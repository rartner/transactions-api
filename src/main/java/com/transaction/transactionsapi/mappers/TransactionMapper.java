package com.transaction.transactionsapi.mappers;

import org.springframework.stereotype.Service;

import com.transaction.transactionsapi.dtos.transaction.TransactionRequestDTO;
import com.transaction.transactionsapi.entities.Account;
import com.transaction.transactionsapi.entities.Transaction;
import com.transaction.transactionsapi.enums.OperationType;

@Service
public class TransactionMapper {

	public Transaction toEntity(TransactionRequestDTO requestDTO) {
		return Transaction.builder()
				.amount( requestDTO.getAmount() )
				.operationType( OperationType.fromId( requestDTO.getOperationTypeId() ) )
				.account( Account.builder().id( requestDTO.getAccountId() ).build() )
				.build();
	}
}
