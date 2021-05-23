package com.transaction.transactionsapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.transactionsapi.dtos.account.AccountRequestDTO;
import com.transaction.transactionsapi.dtos.account.AccountResponseDTO;
import com.transaction.transactionsapi.entities.Account;
import com.transaction.transactionsapi.mappers.AccountMapper;
import com.transaction.transactionsapi.services.AccountService;

@RestController
@RequestMapping("accounts")
public class AccountController {

	private final AccountService accountService;
	private final AccountMapper accountMapper;

	@Autowired
	public AccountController(AccountService service, AccountMapper mapper) {
		this.accountService = service;
		this.accountMapper = mapper;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountResponseDTO> create(@Valid @RequestBody AccountRequestDTO accountRequestDTO) {
		Account account = accountService.create( accountMapper.toEntity( accountRequestDTO ) );

		return ResponseEntity.status( HttpStatus.CREATED ).body( accountMapper.toDTO( account ) );
	}

	@GetMapping("{id}")
	public ResponseEntity<AccountResponseDTO> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok( accountMapper.toDTO( accountService.findById( id ) ) );
	}
}
