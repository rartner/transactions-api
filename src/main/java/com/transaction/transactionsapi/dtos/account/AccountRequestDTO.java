package com.transaction.transactionsapi.dtos.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class AccountRequestDTO {

	@ApiModelProperty(value = "The customer document number", required = true)
	@NotNull(message = "document_number cannot be null")
	@NotBlank(message = "document_number cannot be blank")
	@JsonProperty("document_number")
	private String documentNumber;
}
