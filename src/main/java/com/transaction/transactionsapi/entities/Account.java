package com.transaction.transactionsapi.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Account extends AuditableEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String documentNumber;

	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions;
}
