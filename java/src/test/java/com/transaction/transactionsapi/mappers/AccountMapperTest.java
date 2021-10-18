package com.transaction.transactionsapi.mappers;

import static com.transaction.transactionsapi.mocks.AccountMock.getAccountRequestDTO;
import static com.transaction.transactionsapi.mocks.AccountMock.getSavedAccount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

import com.transaction.transactionsapi.dtos.account.AccountRequestDTO;
import com.transaction.transactionsapi.dtos.account.AccountResponseDTO;
import com.transaction.transactionsapi.entities.Account;

class AccountMapperTest {

	private final AccountMapper mapper = new AccountMapper();

	@Test
	void givenValidRequestDTOWhenMappingToEntityThenReturnAccount() {
		AccountRequestDTO requestDTO = getAccountRequestDTO();

		Account entity = mapper.toEntity( requestDTO );

		assertThat( entity.getDocumentNumber(), equalTo( requestDTO.getDocumentNumber() ) );
	}

	@Test
	void givenValidEntityWhenMappingToDTOThenReturnResponseDTO() {
		Account entity = getSavedAccount();

		AccountResponseDTO responseDTO = mapper.toDTO( entity );

		assertThat( responseDTO.getId(), equalTo( entity.getId() ) );
		assertThat( responseDTO.getDocumentNumber(), equalTo( entity.getDocumentNumber() ) );
	}
}
