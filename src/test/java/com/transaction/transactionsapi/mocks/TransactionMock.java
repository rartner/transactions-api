package com.transaction.transactionsapi.mocks;

import static com.transaction.transactionsapi.mocks.AccountMock.getSavedAccount;

import java.math.BigDecimal;

import com.transaction.transactionsapi.dtos.transaction.TransactionRequestDTO;
import com.transaction.transactionsapi.entities.Account;
import com.transaction.transactionsapi.entities.Transaction;
import com.transaction.transactionsapi.enums.OperationType;

public class TransactionMock {

	public static final Long ID = 456L;
	public static final Long ACCOUNT_ID = 1L;
	public static final OperationType OPERATION_TYPE = OperationType.PAYMENT;
	public static final OperationType OPERATION_TYPE_NEG = OperationType.PURCHASE_CASH;
	public static final BigDecimal AMOUNT = BigDecimal.valueOf( 123.45 );

	public static TransactionRequestDTO getTransactionRequestDTO() {
		return TransactionRequestDTO.builder()
				.accountId( ACCOUNT_ID )
				.amount( AMOUNT )
				.operationTypeId( OPERATION_TYPE.getId() )
				.build();
	}

	public static Transaction getTransaction() {
		return getBaseTransaction();
	}

	public static Transaction getSavedTransaction() {
		Transaction transaction = getBaseTransaction();
		transaction.setId( ID );
		transaction.setAccount( getSavedAccount() );

		return transaction;
	}

	public static Transaction getNegativeTransaction() {
		Transaction transaction = getBaseTransaction();
		transaction.setOperationType( OPERATION_TYPE_NEG );

		return transaction;
	}

	public static Transaction getSavedNegativeTransaction() {
		Transaction transaction = getSavedTransaction();
		transaction.setOperationType( OPERATION_TYPE_NEG );
		transaction.setAmount( AMOUNT.negate() );

		return transaction;
	}

	private static Transaction getBaseTransaction() {
		return Transaction.builder()
				.account( Account.builder().id( ACCOUNT_ID ).build() )
				.operationType( OPERATION_TYPE )
				.amount( AMOUNT )
				.build();
	}
}
