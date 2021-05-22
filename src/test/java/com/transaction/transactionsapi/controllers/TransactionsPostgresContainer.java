package com.transaction.transactionsapi.controllers;

import org.testcontainers.containers.PostgreSQLContainer;

public class TransactionsPostgresContainer extends PostgreSQLContainer<TransactionsPostgresContainer> {

	private static final String IMAGE_VERSION = "postgres:11";
	private static TransactionsPostgresContainer container;

	private TransactionsPostgresContainer() {
		super( IMAGE_VERSION );
	}

	public static TransactionsPostgresContainer getInstance() {
		if (container == null) {
			container = new TransactionsPostgresContainer();
		}

		return container;
	}

	@Override
	public void start() {
		super.start();
		System.setProperty( "DB_URL", container.getJdbcUrl() );
		System.setProperty( "DB_USERNAME", container.getUsername() );
		System.setProperty( "DB_PASSWORD", container.getPassword() );
	}
}
