package com.transaction.transactionsapi.services;

import static com.transaction.transactionsapi.mocks.AccountMock.getSavedAccount;
import static com.transaction.transactionsapi.mocks.TransactionMock.ACCOUNT_ID;
import static com.transaction.transactionsapi.mocks.TransactionMock.AMOUNT;
import static com.transaction.transactionsapi.mocks.TransactionMock.getNegativeTransaction;
import static com.transaction.transactionsapi.mocks.TransactionMock.getSavedNegativeTransaction;
import static com.transaction.transactionsapi.mocks.TransactionMock.getSavedTransaction;
import static com.transaction.transactionsapi.mocks.TransactionMock.getTransaction;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transaction.transactionsapi.entities.Transaction;
import com.transaction.transactionsapi.exceptions.AccountNotFoundException;
import com.transaction.transactionsapi.repositories.TransactionRepository;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

	@InjectMocks
	private TransactionService transactionService;
	@Mock
	private TransactionRepository transactionRepository;
	@Mock
	private AccountService accountService;
	@Captor
	private ArgumentCaptor<Transaction> transactionArgumentCaptor;

	@Test
	void givenValidTransactionWhenCreatingTransactionThenSuccess() {
		Transaction transaction = getTransaction();
		doReturn( getSavedAccount() ).when( accountService ).findById( ACCOUNT_ID );
		doReturn( getSavedTransaction() ).when( transactionRepository ).save( transaction );

		Transaction createdTransaction = transactionService.create( transaction );

		assertThat( createdTransaction.getAmount(), equalTo( transaction.getAmount() ) );
		assertThat( createdTransaction.getOperationType(), equalTo( transaction.getOperationType() ) );
		assertThat( createdTransaction.getAccount().getId(), equalTo( transaction.getAccount().getId() ) );
		assertNotNull( createdTransaction.getId() );
	}

	@Test
	void givenPurchaseTransactionWhenCreatingTransactionThenSaveWithNegativeValue() {
		Transaction transaction = getNegativeTransaction();
		doReturn( getSavedAccount() ).when( accountService ).findById( ACCOUNT_ID );
		doReturn( getSavedNegativeTransaction() ).when( transactionRepository )
				.save( transactionArgumentCaptor.capture() );

		Transaction createdTransaction = transactionService.create( transaction );

		assertThat( createdTransaction.getOperationType(), equalTo( transaction.getOperationType() ) );
		assertThat( transactionArgumentCaptor.getValue().getAmount(), equalTo( AMOUNT.negate() ) );
	}

	@Test
	void givenNonexistentAccountIdWhenCreatingTransactionThenThrowAccountNotFoundException() {
		Transaction transaction = getTransaction();
		doThrow( AccountNotFoundException.class ).when( accountService ).findById( ACCOUNT_ID );

		assertThrows( AccountNotFoundException.class, () -> {
			transactionService.create( transaction );
		} );
	}
}
