package com.transaction.transactionsapi.controllers;

import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.transaction.transactionsapi.TransactionsApiApplication;

@ActiveProfiles("integration")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		TransactionsApiApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integration.properties")
public abstract class SpringControllerTest {

	@ClassRule
	public static TransactionsPostgresContainer postgres = TransactionsPostgresContainer.getInstance();

	@BeforeAll
	static void initialize() {
		postgres.start();
	}
}
