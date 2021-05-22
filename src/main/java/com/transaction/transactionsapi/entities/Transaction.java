package com.transaction.transactionsapi.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.transaction.transactionsapi.enums.OperationType;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Transaction extends AuditableEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(value = EnumType.STRING)
	private OperationType operationType;

	private BigDecimal amount;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
}
