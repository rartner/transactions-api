package com.transaction.transactionsapi.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

class StatusControllerTest extends SpringControllerTest {

	@Autowired
	private MockMvc mock;

	@Test
	void givenStatusRequestWhenServerIsRunningThenReturnOK() throws Exception {
		mock.perform( get( "/status" ) )
				.andExpect( status().isOk() )
				.andExpect( content().string( is( "OK" ) ));
	}
}
