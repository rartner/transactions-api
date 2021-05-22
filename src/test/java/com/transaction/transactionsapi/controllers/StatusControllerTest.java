package com.transaction.transactionsapi.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatusControllerTest {

	@Autowired
	private MockMvc mock;

	@Test
	public void givenStatusRequestWhenServerIsRunningThenReturnOK() throws Exception {
		mock.perform( get( "/status" ) )
				.andExpect( status().isOk() )
				.andExpect( content().string( containsString( "OK" ) ));
	}
}
