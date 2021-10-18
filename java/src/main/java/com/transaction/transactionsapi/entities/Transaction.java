package com.transaction.transactionsapi.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.transaction.transactionsapi.enums.OperationType;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@Entity
@Builder
@SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_sequence")
public class Transaction extends AuditableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
	private Long id;

	@Enumerated(value = EnumType.STRING)
	private OperationType operationType;

	private BigDecimal amount;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
}
