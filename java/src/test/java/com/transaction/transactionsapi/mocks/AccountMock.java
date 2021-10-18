package com.transaction.transactionsapi.mocks;

import com.transaction.transactionsapi.dtos.account.AccountRequestDTO;
import com.transaction.transactionsapi.entities.Account;

public class AccountMock {

	public static final String DOCUMENT_NUMBER = "08989909923";
	public static final Long ACCOUNT_ID = 123L;

	public static Account getAccount() {
		return Account.builder().documentNumber( DOCUMENT_NUMBER ).build();
	}

	public static Account getSavedAccount() {
		return Account.builder().id( ACCOUNT_ID ).documentNumber( DOCUMENT_NUMBER ).build();
	}

	public static AccountRequestDTO getAccountRequestDTO() {
		return AccountRequestDTO.builder().documentNumber( DOCUMENT_NUMBER ).build();
	}
}
