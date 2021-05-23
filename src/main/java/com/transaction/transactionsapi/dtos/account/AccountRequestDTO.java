package com.transaction.transactionsapi.dtos.account;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@NoArgsConstructor
@Getter
@Setter
public class AccountRequestDTO {

	@NotNull(message = "Document number cannot be null")
	@JsonProperty("document_number")
	private String documentNumber;
}
