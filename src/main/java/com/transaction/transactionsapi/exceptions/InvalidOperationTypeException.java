package com.transaction.transactionsapi.exceptions;

public class InvalidOperationTypeException extends RuntimeException {

	public InvalidOperationTypeException() {
		super( "The operation type informed is invalid" );
	}
}
