package com.transaction.transactionsapi.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.transaction.transactionsapi.exceptions.InvalidOperationTypeException;

@AllArgsConstructor
@Getter
public enum OperationType {

	PURCHASE_CASH( 1L, "COMPRA A VISTA" ),
	PURCHASE_INSTALLMENTS( 2L, "COMPRA PARCELADA" ),
	WITHDRAW( 3L, "SAQUE" ),
	PAYMENT( 4L, "PAGAMENTO" );

	private final Long id;
	private final String description;

	public static OperationType fromId(Long id) {
		return Arrays.stream( OperationType.values() )
				.filter( operationType -> operationType.getId().equals( id ) )
				.findFirst()
				.orElseThrow( InvalidOperationTypeException::new );
	}

	public Boolean amountShouldBeNegative() {
		return PURCHASE_CASH.equals( this ) || PURCHASE_INSTALLMENTS.equals( this ) || WITHDRAW.equals( this );
	}
}
