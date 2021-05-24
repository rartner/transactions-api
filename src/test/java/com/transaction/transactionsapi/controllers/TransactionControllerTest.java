package com.transaction.transactionsapi.controllers;

import static com.transaction.transactionsapi.mocks.TransactionMock.AMOUNT;
import static com.transaction.transactionsapi.mocks.TransactionMock.getTransactionRequestDTO;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.transactionsapi.dtos.transaction.TransactionRequestDTO;

class TransactionControllerTest extends SpringControllerTest {

	private static final String BASE_PATH = "/transactions";
	private static final String INVALID_DATA_MESSAGE = "Your request body contains invalid data";
	private static final String ACCOUNT_NOT_FOUND_MESSAGE = "Account not found for the given id";
	private static final String INVALID_OPERATION_TYPE_MESSAGE = "The operation type informed is invalid";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void givenValidTransactionWhenCreateTransactionThenReturnSuccess() throws Exception {
		createAccount();

		performPost( getTransactionRequestDTO() )
				.andExpect( status().isCreated() );
	}

	@Test
	void givenInvalidOperationTypeWhenCreatingTransactionThenReturnInvalidOperationTypeError() throws Exception {
		TransactionRequestDTO requestDTO = getTransactionRequestDTO();
		requestDTO.setOperationTypeId( 55L );

		performPost( requestDTO )
				.andExpect( status().isUnprocessableEntity() )
				.andExpect( jsonPath( "$.status", is( HttpStatus.UNPROCESSABLE_ENTITY.value() ) ) )
				.andExpect( jsonPath( "$.message", is( INVALID_OPERATION_TYPE_MESSAGE ) ) );
	}

	@Test
	void givenNonexistentAccountIdWhenCreatingTransactionThenReturnAccountNotFoundException() throws Exception {
		TransactionRequestDTO requestDTO = getTransactionRequestDTO();
		requestDTO.setAccountId( 55L );

		performPost( requestDTO )
				.andExpect( status().isNotFound() )
				.andExpect( jsonPath( "$.status", is( HttpStatus.NOT_FOUND.value() ) ) )
				.andExpect( jsonPath( "$.message", is( ACCOUNT_NOT_FOUND_MESSAGE ) ) );
	}

	@Test
	void givenNegativeAmountWhenCreatingTransactionThenReturnValidationError() throws Exception {
		createAccount();

		TransactionRequestDTO requestDTO = getTransactionRequestDTO();
		requestDTO.setAmount( AMOUNT.negate() );

		performPost( requestDTO )
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath( "$.status", is( HttpStatus.BAD_REQUEST.value() ) ) )
				.andExpect( jsonPath( "$.message", is( INVALID_DATA_MESSAGE ) ) )
				.andExpect( jsonPath( "$.details[0]", is( "amount must be positive" ) ) );
	}

	private ResultActions performPost(TransactionRequestDTO requestDTO) throws Exception {
		return mockMvc.perform( post( BASE_PATH )
				.contentType( MediaType.APPLICATION_JSON )
				.content( new ObjectMapper().writeValueAsString( requestDTO ) ) );
	}
}
