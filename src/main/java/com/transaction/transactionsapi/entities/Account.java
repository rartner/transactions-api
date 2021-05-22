package com.transaction.transactionsapi.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Account {

	@Id
	@GeneratedValue
	private Long id;

	private String documentNumber;

	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions;
}
