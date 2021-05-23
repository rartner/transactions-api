package com.transaction.transactionsapi.dtos.error;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@AllArgsConstructor
public class ErrorDTO {

	private Integer status;
	private String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> errors;

	public ErrorDTO(Integer status, String message) {
		this.status = status;
		this.message = message;
	}
}
