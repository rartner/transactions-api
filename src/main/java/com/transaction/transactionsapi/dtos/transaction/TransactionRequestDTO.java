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

import io.swagger.annotations.ApiModelProperty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionRequestDTO {

	@ApiModelProperty(value = "The customer account id", required = true, dataType = "Long", example = "123")
	@NotNull(message = "account_id cannot be null")
	@Positive(message = "account_id must be positive")
	@JsonProperty("account_id")
	private Long accountId;

	@ApiModelProperty(value = "The operation type id. 1: PURCHASE_CASH. 2: PURCHASE_INSTALLMENTS. 3: WITHDRAW. 4: PAYMENTS", required = true, dataType = "Long", example = "1")
	@NotNull(message = "operation_type_id cannot be null")
	@Positive(message = "operation_type_id must be positive")
	@JsonProperty("operation_type_id")
	private Long operationTypeId;

	@ApiModelProperty(value = "The value of the transaction. It should be positive", required = true, dataType = "BigDecimal", example = "123.45")
	@NotNull(message = "amount cannot be null")
	@Positive(message = "amount must be positive")
	private BigDecimal amount;
}
