package com.transaction.transactionsapi.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.transaction.transactionsapi.exceptions.InvalidOperationTypeException;

@AllArgsConstructor
@Getter
public enum OperationType {

	PURCHASE_CASH( 1L, "COMPRA A VISTA", true ),
	PURCHASE_INSTALLMENTS( 2L, "COMPRA PARCELADA", true ),
	WITHDRAW( 3L, "SAQUE", true ),
	PAYMENT( 4L, "PAGAMENTO", false );

	private final Long id;
	private final String description;
	private final boolean isNegative;

	public static OperationType fromId(Long id) {
		return Arrays.stream( OperationType.values() )
				.filter( operationType -> operationType.getId().equals( id ) )
				.findFirst()
				.orElseThrow( InvalidOperationTypeException::new );
	}
}
