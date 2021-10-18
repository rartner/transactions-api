package com.transaction.transactionsapi.controllers;

import static com.transaction.transactionsapi.mocks.AccountMock.getAccountRequestDTO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("integration")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integration.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class SpringControllerTest {

	@ClassRule
	public static TransactionsPostgresContainer postgres = TransactionsPostgresContainer.getInstance();
	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	static void initialize() {
		postgres.start();
	}

	protected void createAccount() throws Exception {
		mockMvc.perform( post( "/accounts" )
				.contentType( MediaType.APPLICATION_JSON )
				.content( new ObjectMapper().writeValueAsBytes( getAccountRequestDTO() ) ) );
	}
}
