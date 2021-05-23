package com.transaction.transactionsapi.services;

import static com.transaction.transactionsapi.mocks.AccountMock.ACCOUNT_ID;
import static com.transaction.transactionsapi.mocks.AccountMock.DOCUMENT_NUMBER;
import static com.transaction.transactionsapi.mocks.AccountMock.getAccount;
import static com.transaction.transactionsapi.mocks.AccountMock.getSavedAccount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.transaction.transactionsapi.entities.Account;
import com.transaction.transactionsapi.exceptions.AccountNotFoundException;
import com.transaction.transactionsapi.exceptions.DocumentAlreadyUsedException;
import com.transaction.transactionsapi.repositories.AccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	@InjectMocks
	private AccountService accountService;
	@Mock
	private AccountRepository accountRepository;

	@Test
	public void givenValidDocumentWhenCreatingAccountThenCreateNewAccount() {
		Account validAccount = getAccount();
		Account savedAccount = getSavedAccount();

		doReturn( Optional.empty() ).when( accountRepository ).findByDocumentNumber( DOCUMENT_NUMBER );
		doReturn( savedAccount ).when( accountRepository ).save( validAccount );

		Account createdAccount = accountService.create( validAccount );

		assertThat( createdAccount.getDocumentNumber(), equalTo( validAccount.getDocumentNumber() ) );
		assertNotNull( createdAccount.getId() );
	}

	@Test
	public void givenDocumentAlreadyInUseWhenCreatingAccountThenThrowException() {
		Account account = getAccount();
		doReturn( Optional.of( getSavedAccount() ) ).when( accountRepository ).findByDocumentNumber( DOCUMENT_NUMBER );

		assertThrows( DocumentAlreadyUsedException.class, () -> {
			accountService.create( account );
		} );
	}

	@Test
	public void givenExistingIdWhenFindByIdThenReturnAccount() {
		doReturn( Optional.of( getSavedAccount() ) ).when( accountRepository ).findById( ACCOUNT_ID );

		Account account = accountService.findById( ACCOUNT_ID );

		assertThat( account.getId(), equalTo( ACCOUNT_ID ) );
		assertNotNull( account.getDocumentNumber() );
	}

	@Test
	public void givenNonexistentIdWhenFindByIdThenThrowNotFoundException() {
		doReturn( Optional.empty() ).when( accountRepository ).findById( ACCOUNT_ID );

		assertThrows( AccountNotFoundException.class, () -> {
			accountService.findById( ACCOUNT_ID );
		} );
	}
}
