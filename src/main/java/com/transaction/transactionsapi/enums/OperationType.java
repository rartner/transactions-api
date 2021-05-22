package com.transaction.transactionsapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationType {

	PURCHASE_CASH(1L, "COMPRA A VISTA"),
	PURCHASE_INSTALLMENTS(2L, "COMPRA PARCELADA"),
	WITHDRAW(3L, "SAQUE"),
	PAYMENT(4L, "PAGAMENTO");

	private final Long id;
	private final String description;
}
