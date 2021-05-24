package com.transaction.transactionsapi.mappers;

import static com.transaction.transactionsapi.mocks.TransactionMock.getTransactionRequestDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.transaction.transactionsapi.dtos.transaction.TransactionRequestDTO;
import com.transaction.transactionsapi.entities.Transaction;
import com.transaction.transactionsapi.exceptions.InvalidOperationTypeException;

class TransactionMapperTest {

	private final TransactionMapper mapper = new TransactionMapper();

	@Test
	void givenValidTransactionRequestDTOWhenMappingToEntityThenReturnTransaction() {
		TransactionRequestDTO requestDTO = getTransactionRequestDTO();

		Transaction entity = mapper.toEntity( requestDTO );

		assertThat( entity.getOperationType().getId(), equalTo( requestDTO.getOperationTypeId() ) );
		assertThat( entity.getAmount(), equalTo( requestDTO.getAmount() ) );
		assertThat( entity.getAccount().getId(), equalTo( requestDTO.getAccountId() ) );
	}

	@Test
	void givenRequestWithInvalidOperationTypeIdWhenMappingToEntityThenThrowInvalidOperationTypeException() {
		TransactionRequestDTO requestDTO = getTransactionRequestDTO();
		requestDTO.setOperationTypeId( 100L );

		assertThrows( InvalidOperationTypeException.class, () -> {
			mapper.toEntity( requestDTO );
		} );
	}
}
