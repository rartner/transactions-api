package com.transaction.transactionsapi.controllers;

import static com.transaction.transactionsapi.mocks.AccountMock.DOCUMENT_NUMBER;
import static com.transaction.transactionsapi.mocks.AccountMock.getAccountRequestDTO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountControllerTest extends SpringControllerTest {

	private static final String ACCOUNT_ALREADY_EXISTS_MESSAGE = "An account with this document number already exists";
	private static final String BASE_PATH = "/accounts";
	private static final String ACCOUNT_NOT_FOUND_MESSAGE = "Account not found for the given id";
	private static final String INVALID_DATA_MESSAGE = "Your request body contains invalid data";

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	public void givenValidRequestWhenCreatingAccountThenSuccess() throws Exception {
		mockMvc.perform( post( BASE_PATH )
				.contentType( MediaType.APPLICATION_JSON )
				.content( new ObjectMapper().writeValueAsBytes( getAccountRequestDTO() ) ) )
				.andExpect( status().isCreated() )
				.andExpect( jsonPath( "$.account_id", is( 1 ) ) )
				.andExpect( jsonPath( "$.document_number", is( DOCUMENT_NUMBER ) ) );
	}

	@Test
	@Order(2)
	public void givenAlreadyRegisteredDocumentWhenCreatingAccountThenReturnUnprocessableEntity() throws Exception {
		mockMvc.perform( post( BASE_PATH ).contentType( MediaType.APPLICATION_JSON )
				.content( new ObjectMapper().writeValueAsString( getAccountRequestDTO() ) ) )
				.andExpect( status().isUnprocessableEntity() )
				.andExpect( jsonPath( "$.status", is( HttpStatus.UNPROCESSABLE_ENTITY.value() ) ) )
				.andExpect( jsonPath( "$.message", is( ACCOUNT_ALREADY_EXISTS_MESSAGE ) ) );
	}

	@Test
	@Order(3)
	public void givenInvalidDTOWhenCreatingAccountThenReturnErrorWithMessages() throws Exception {
		mockMvc.perform( post( BASE_PATH ).contentType( MediaType.APPLICATION_JSON )
				.content( "{}" ) )
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath( "$.status", is( HttpStatus.BAD_REQUEST.value() ) ) )
				.andExpect( jsonPath( "$.message", is( INVALID_DATA_MESSAGE ) ) )
				.andExpect( jsonPath( "$.details[0]", matchesRegex( "document_number cannot be (blank|null)" ) ) )
				.andExpect( jsonPath( "$.details[1]", matchesRegex( "document_number cannot be (blank|null)" ) ) );
	}

	@Test
	@Order(4)
	public void givenExistingIdWhenFindAccountByIdThenReturnAccount() throws Exception {
		mockMvc.perform( get( BASE_PATH + "/1" ) )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.account_id", is( 1 ) ) )
				.andExpect( jsonPath( "$.document_number", is( DOCUMENT_NUMBER ) ) );
	}

	@Test
	@Order(5)
	public void givenNonexistentIdWhenFindAccountByIdThenReturnNotFound() throws Exception {
		mockMvc.perform( get( BASE_PATH + "/123" ) )
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath( "$.status", is( HttpStatus.NOT_FOUND.value() ) ) )
				.andExpect( jsonPath( "$.message", is( ACCOUNT_NOT_FOUND_MESSAGE ) ) );
	}
}
