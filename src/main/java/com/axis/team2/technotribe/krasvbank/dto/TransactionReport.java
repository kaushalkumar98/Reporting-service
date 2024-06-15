package com.axis.team2.technotribe.krasvbank.dto;

import java.math.BigDecimal;
import java.util.List;

import com.axis.team2.technotribe.krasvbank.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionReport {
	private List<Transaction> transactions;
    private BigDecimal totalAmount;
}
