package com.transaction.transactionsapi.mappers;

import org.springframework.stereotype.Service;

import com.transaction.transactionsapi.dtos.account.AccountRequestDTO;
import com.transaction.transactionsapi.dtos.account.AccountResponseDTO;
import com.transaction.transactionsapi.entities.Account;

@Service
public class AccountMapper {

	public Account toEntity(AccountRequestDTO request) {
		return Account.builder()
				.documentNumber( request.getDocumentNumber() )
				.build();
	}

	public AccountResponseDTO toDTO(Account account) {
		return AccountResponseDTO.builder()
				.id( account.getId() )
				.documentNumber( account.getDocumentNumber() )
				.build();
	}
}
