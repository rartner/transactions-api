package com.transaction.transactionsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = { "com.transaction.transactionsapi.entities" })
public class TransactionsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run( TransactionsApiApplication.class, args );
	}

}
