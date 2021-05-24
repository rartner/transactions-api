package com.transaction.transactionsapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.transaction.transactionsapi.dtos.error.ErrorDTO;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(AccountNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	protected ErrorDTO handleAccountNotFoundException(AccountNotFoundException exception) {
		return new ErrorDTO( HttpStatus.NOT_FOUND.value(), exception.getMessage() );
	}

	@ExceptionHandler(DocumentAlreadyUsedException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	protected ErrorDTO handleDocumentAlreadyUsedException(DocumentAlreadyUsedException exception) {
		return new ErrorDTO( HttpStatus.UNPROCESSABLE_ENTITY.value(), exception.getMessage() );
	}

	@ExceptionHandler(InvalidOperationTypeException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	protected ErrorDTO handleInvalidOperationTypeException(InvalidOperationTypeException exception) {
		return new ErrorDTO( HttpStatus.UNPROCESSABLE_ENTITY.value(), exception.getMessage() );
	}
}
