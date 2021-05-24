package com.transaction.transactionsapi.dtos.transaction;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionRequestDTO {

	@NotNull(message = "account_id cannot be null")
	@Positive(message = "account_id must be positive")
	@JsonProperty("account_id")
	private Long accountId;

	@NotNull(message = "operation_type_id cannot be null")
	@Positive(message = "operation_type_id must be positive")
	@JsonProperty("operation_type_id")
	private Long operationTypeId;

	@NotNull(message = "amount cannot be null")
	@Positive(message = "amount must be positive")
	private BigDecimal amount;
}
