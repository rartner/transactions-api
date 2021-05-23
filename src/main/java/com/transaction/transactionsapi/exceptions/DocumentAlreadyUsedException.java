package com.transaction.transactionsapi.exceptions;

public class DocumentAlreadyUsedException extends RuntimeException {

	public DocumentAlreadyUsedException() {
		super( "An account with this document number already exists." );
	}
}
