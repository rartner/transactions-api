package com.transaction.transactionsapi.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.transaction.transactionsapi.dtos.error.ErrorDTO;

@ControllerAdvice
public class ValidationAdvice extends ResponseEntityExceptionHandler {

	public static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = ex.getBindingResult().getAllErrors().stream()
				.map( ObjectError::getDefaultMessage )
				.collect( Collectors.toList() );

		return ResponseEntity.status( STATUS )
				.body( new ErrorDTO( STATUS.value(), "Your request body has some invalid data", errors ) );
	}
}
