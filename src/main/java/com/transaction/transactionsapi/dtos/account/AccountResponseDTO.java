package com.transaction.transactionsapi.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountResponseDTO {

	@JsonProperty("account_id")
	private Long id;

	@JsonProperty("document_number")
	private String documentNumber;
}
