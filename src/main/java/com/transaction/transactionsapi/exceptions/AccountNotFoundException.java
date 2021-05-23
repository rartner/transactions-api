package com.transaction.transactionsapi.exceptions;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException() {
		super( "Account not found for the given id" );
	}
}
