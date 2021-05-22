package com.transaction.transactionsapi.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class StatusControllerTest extends SpringControllerTest {

	@Autowired
	private MockMvc mock;

	@Test
	public void givenStatusRequestWhenServerIsRunningThenReturnOK() throws Exception {
		mock.perform( get( "/status" ) )
				.andExpect( status().isOk() )
				.andExpect( content().string( containsString( "OK" ) ));
	}
}
