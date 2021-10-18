package com.transaction.transactionsapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.transactionsapi.dtos.transaction.TransactionRequestDTO;
import com.transaction.transactionsapi.mappers.TransactionMapper;
import com.transaction.transactionsapi.services.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Transactions", tags = "Transactions")
@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private final TransactionService transactionService;
	private final TransactionMapper transactionMapper;

	@Autowired
	public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
		this.transactionService = transactionService;
		this.transactionMapper = transactionMapper;
	}

	@ApiOperation(value = "Create a transaction")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity create(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {
		transactionService.create( transactionMapper.toEntity( transactionRequestDTO ) );

		return ResponseEntity.status( HttpStatus.CREATED ).build();
	}
}
