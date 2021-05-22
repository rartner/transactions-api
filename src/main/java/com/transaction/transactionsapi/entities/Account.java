package com.transaction.transactionsapi.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Account extends AuditableEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String documentNumber;

	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions;
}
