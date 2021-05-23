package com.transaction.transactionsapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.transactionsapi.entities.Account;
import com.transaction.transactionsapi.entities.Transaction;
import com.transaction.transactionsapi.repositories.TransactionRepository;

@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;
	private final AccountService accountService;

	@Autowired
	public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
		this.transactionRepository = transactionRepository;
		this.accountService = accountService;
	}

	public Transaction create(Transaction transaction) {
		Account account = accountService.findById( transaction.getAccount().getId() );

		transaction.setAccount( account );
		configureTransactionAmount( transaction );

		return transactionRepository.save( transaction );
	}

	private void configureTransactionAmount(Transaction transaction) {
		if (amountShouldBeNegative( transaction ))
			transaction.setAmount( transaction.getAmount().negate() );
	}

	private boolean amountShouldBeNegative(Transaction transaction) {
		return transaction.getOperationType().amountShouldBeNegative();
	}
}
