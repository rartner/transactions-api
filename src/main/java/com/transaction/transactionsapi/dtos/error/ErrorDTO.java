package com.transaction.transactionsapi.dtos.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {

	private Integer status;
	private String message;
}
