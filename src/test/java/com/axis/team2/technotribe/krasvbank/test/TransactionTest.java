package com.axis.team2.technotribe.krasvbank.test;


import org.junit.jupiter.api.Test;

import com.axis.team2.technotribe.krasvbank.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

 class TransactionTest {

    @Test
    void testTransactionEntity() {
        // Test builder
        Transaction transaction = Transaction.builder()
                .transactionId("123e4567-e89b-12d3-a456-426614174000")
                .transactionType("Credit")
                .amount(BigDecimal.valueOf(1000.00))
                .accountNumber("123456789")
                .status("Completed")
                .createdAt(LocalDateTime.now())
                .build();

        // Test getters
        assertEquals("123e4567-e89b-12d3-a456-426614174000", transaction.getTransactionId());
        assertEquals("Credit", transaction.getTransactionType());
        assertEquals(BigDecimal.valueOf(1000.00), transaction.getAmount());
        assertEquals("123456789", transaction.getAccountNumber());
        assertEquals("Completed", transaction.getStatus());
        assertNotNull(transaction.getCreatedAt());

        // Test setters
        transaction.setTransactionId("987e6543-e21b-32d1-a654-324617410000");
        transaction.setTransactionType("Debit");
        transaction.setAmount(BigDecimal.valueOf(500.00));
        transaction.setAccountNumber("987654321");
        transaction.setStatus("Pending");
        LocalDateTime now = LocalDateTime.now();
        transaction.setCreatedAt(now);

        // Verify updated values
        assertEquals("987e6543-e21b-32d1-a654-324617410000", transaction.getTransactionId());
        assertEquals("Debit", transaction.getTransactionType());
        assertEquals(BigDecimal.valueOf(500.00), transaction.getAmount());
        assertEquals("987654321", transaction.getAccountNumber());
        assertEquals("Pending", transaction.getStatus());
        assertEquals(now, transaction.getCreatedAt());
    }

    @Test
    void testNoArgsConstructor() {
        // Test no-args constructor
        Transaction transaction = new Transaction();

        // Verify default values
        assertNull(transaction.getTransactionId());
        assertNull(transaction.getTransactionType());
        assertNull(transaction.getAmount());
        assertNull(transaction.getAccountNumber());
        assertNull(transaction.getStatus());
        assertNull(transaction.getCreatedAt());
    }

    @Test
    void testAllArgsConstructor() {
        // Test all-args constructor
        LocalDateTime now = LocalDateTime.now();
        Transaction transaction = new Transaction(
                "123e4567-e89b-12d3-a456-426614174000",
                "Credit",
                BigDecimal.valueOf(1000.00),
                "123456789",
                "Completed",
                now
        );

        // Verify values
        assertEquals("123e4567-e89b-12d3-a456-426614174000", transaction.getTransactionId());
        assertEquals("Credit", transaction.getTransactionType());
        assertEquals(BigDecimal.valueOf(1000.00), transaction.getAmount());
        assertEquals("123456789", transaction.getAccountNumber());
        assertEquals("Completed", transaction.getStatus());
        assertEquals(now, transaction.getCreatedAt());
    }
}

