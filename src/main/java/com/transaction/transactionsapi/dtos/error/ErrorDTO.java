package com.transaction.transactionsapi.dtos.error;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@AllArgsConstructor
public class ErrorDTO {

	private final Integer status;
	private final String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> details;

	public ErrorDTO(Integer status, String message) {
		this.status = status;
		this.message = message;
	}
}
