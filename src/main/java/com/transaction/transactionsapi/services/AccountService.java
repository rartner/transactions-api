package com.transaction.transactionsapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.transactionsapi.entities.Account;
import com.transaction.transactionsapi.exceptions.AccountNotFoundException;
import com.transaction.transactionsapi.exceptions.DocumentAlreadyUsedException;
import com.transaction.transactionsapi.repositories.AccountRepository;

@Service
public class AccountService {

	private final AccountRepository accountRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Account create(Account account) {
		if (documentAlreadyUsed( account.getDocumentNumber() ))
			throw new DocumentAlreadyUsedException();

		return accountRepository.save( account );
	}

	private boolean documentAlreadyUsed(String documentNumber) {
		return accountRepository.findByDocumentNumber( documentNumber ).isPresent();
	}

	public Account findById(Long id) {
		return accountRepository.findById( id ).orElseThrow( AccountNotFoundException::new );
	}
}
