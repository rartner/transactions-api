package com.transaction.transactionsapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Status", tags = "Status")
@RestController
@RequestMapping("status")
public class StatusController {

	@ApiOperation(value = "Get application current status")
	@GetMapping
	public String status() {
		return "OK";
	}
}
