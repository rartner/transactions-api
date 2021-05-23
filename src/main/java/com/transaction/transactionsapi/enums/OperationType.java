package com.transaction.transactionsapi.enums;

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
		if (id == 1)
			return PURCHASE_CASH;
		if (id == 2)
			return PURCHASE_INSTALLMENTS;
		if (id == 3)
			return WITHDRAW;
		if (id == 4)
			return PAYMENT;

		throw new InvalidOperationTypeException();
	}

	public Boolean amountShouldBeNegative() {
		return PURCHASE_CASH.equals( this ) || PURCHASE_INSTALLMENTS.equals( this ) || WITHDRAW.equals( this );
	}
}
