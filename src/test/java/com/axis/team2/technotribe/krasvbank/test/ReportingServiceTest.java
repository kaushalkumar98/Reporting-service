package com.axis.team2.technotribe.krasvbank.test;


import com.axis.team2.technotribe.krasvbank.dto.TransactionReport;
import com.axis.team2.technotribe.krasvbank.entity.Transaction;
import com.axis.team2.technotribe.krasvbank.entity.User;
import com.axis.team2.technotribe.krasvbank.repository.TransactionRepository;
import com.axis.team2.technotribe.krasvbank.repository.UserRepository;
import com.axis.team2.technotribe.krasvbank.service.ReportingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ReportingServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReportingService reportingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTransactionHistory() {
        String accountNumber = "123456";
        LocalDateTime startDate = LocalDateTime.now().minusDays(30);
        LocalDateTime endDate = LocalDateTime.now();

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", "CREDIT", new BigDecimal("100.00"), accountNumber, "SUCCESS", LocalDateTime.now()));
        transactions.add(new Transaction("2", "DEBIT", new BigDecimal("50.00"), accountNumber, "SUCCESS", LocalDateTime.now()));

        when(transactionRepository.findByAccountNumberAndCreatedAtBetween(accountNumber, startDate, endDate)).thenReturn(transactions);

        TransactionReport report = reportingService.getTransactionHistory(accountNumber, startDate, endDate);

        assertEquals(2, report.getTransactions().size());
        assertEquals(new BigDecimal("150.00"), report.getTotalAmount());
    }

    @Test
    void testGetAllTransactions() {
        String accountNumber = "123456";

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", "CREDIT", new BigDecimal("100.00"), accountNumber, "SUCCESS", LocalDateTime.now()));
        transactions.add(new Transaction("2", "DEBIT", new BigDecimal("50.00"), accountNumber, "SUCCESS", LocalDateTime.now()));
        transactions.add(new Transaction("3", "TRANSFER", new BigDecimal("200.00"), accountNumber, "SUCCESS", LocalDateTime.now()));

        when(transactionRepository.findByAccountNumber(accountNumber)).thenReturn(transactions);

        TransactionReport report = reportingService.getAllTransactions(accountNumber);

        assertEquals(3, report.getTransactions().size());
        assertEquals(new BigDecimal("350.00"), report.getTotalAmount());
    }

    @Test
    void testGetAllCredits() {
        String accountNumber = "123456";

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", "CREDIT", new BigDecimal("100.00"), accountNumber, "SUCCESS", LocalDateTime.now()));

        when(transactionRepository.findByAccountNumberAndTransactionType(accountNumber, "CREDIT")).thenReturn(transactions);

        TransactionReport report = reportingService.getAllCredits(accountNumber);

        assertEquals(1, report.getTransactions().size());
        assertEquals(new BigDecimal("100.00"), report.getTotalAmount());
    }

    @Test
    void testGetAllDebits() {
        String accountNumber = "123456";

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", "DEBIT", new BigDecimal("50.00"), accountNumber, "SUCCESS", LocalDateTime.now()));

        when(transactionRepository.findByAccountNumberAndTransactionType(accountNumber, "DEBIT")).thenReturn(transactions);

        TransactionReport report = reportingService.getAllDebits(accountNumber);

        assertEquals(1, report.getTransactions().size());
        assertEquals(new BigDecimal("50.00"), report.getTotalAmount());
    }

    @Test
    void testGetAllFundTransfers() {
        String accountNumber = "123456";

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", "TRANSFER", new BigDecimal("200.00"), accountNumber, "SUCCESS", LocalDateTime.now()));

        when(transactionRepository.findByAccountNumberAndTransactionType(accountNumber, "TRANSFER")).thenReturn(transactions);

        TransactionReport report = reportingService.getAllFundTransfers(accountNumber);

        assertEquals(1, report.getTransactions().size());
        assertEquals(new BigDecimal("200.00"), report.getTotalAmount());
    }

    @Test
    void testGetAllCreditsForCurrentMonth() {
        String accountNumber = "123456";
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", "CREDIT", new BigDecimal("100.00"), accountNumber, "SUCCESS", LocalDateTime.now()));
        transactions.add(new Transaction("2", "CREDIT", new BigDecimal("150.00"), accountNumber, "SUCCESS", LocalDateTime.now()));

        when(transactionRepository.findByAccountNumberAndTransactionTypeAndCreatedAtBetween(
                accountNumber, "CREDIT", startOfMonth, endOfMonth)).thenReturn(transactions);

        TransactionReport report = reportingService.getAllCreditsForCurrentMonth(accountNumber);

        assertEquals(2, report.getTransactions().size());
        assertEquals(new BigDecimal("250.00"), report.getTotalAmount());
    }

    @Test
    void testGetAllDebitsForCurrentMonth() {
        String accountNumber = "123456";
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("3", "DEBIT", new BigDecimal("50.00"), accountNumber, "SUCCESS", LocalDateTime.now()));
        transactions.add(new Transaction("4", "DEBIT", new BigDecimal("75.00"), accountNumber, "SUCCESS", LocalDateTime.now()));

        when(transactionRepository.findByAccountNumberAndTransactionTypeAndCreatedAtBetween(
                accountNumber, "DEBIT", startOfMonth, endOfMonth)).thenReturn(transactions);

        TransactionReport report = reportingService.getAllDebitsForCurrentMonth(accountNumber);

        assertEquals(2, report.getTransactions().size());
        assertEquals(new BigDecimal("125.00"), report.getTotalAmount());
    }

    @Test
    void testGetAllTransactionsWithinTimeframe() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(30);
        LocalDateTime endDate = LocalDateTime.now();

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", "CREDIT", new BigDecimal("100.00"), "123456", "SUCCESS", LocalDateTime.now()));
        transactions.add(new Transaction("2", "DEBIT", new BigDecimal("50.00"), "123456", "SUCCESS", LocalDateTime.now()));

        when(transactionRepository.findByCreatedAtBetween(startDate, endDate)).thenReturn(transactions);

        TransactionReport report = reportingService.getAllTransactionsWithinTimeframe(startDate, endDate);

        assertEquals(2, report.getTransactions().size());
        assertEquals(new BigDecimal("150.00"), report.getTotalAmount());
    }

    @Test
    void testGetTransactionsByUserWithinTimeframe() {
        String accountNumber = "123456";
        LocalDateTime startDate = LocalDateTime.now().minusDays(30);
        LocalDateTime endDate = LocalDateTime.now();

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", "CREDIT", new BigDecimal("100.00"), accountNumber, "SUCCESS", LocalDateTime.now()));
        transactions.add(new Transaction("2", "DEBIT", new BigDecimal("50.00"), accountNumber, "SUCCESS", LocalDateTime.now()));

        when(transactionRepository.findByAccountNumberAndCreatedAtBetween(accountNumber, startDate, endDate)).thenReturn(transactions);

        TransactionReport report = reportingService.getTransactionsByUserWithinTimeframe(accountNumber, startDate, endDate);

        assertEquals(2, report.getTransactions().size());
        assertEquals(new BigDecimal("150.00"), report.getTotalAmount());
    }

    @Test
    void testGetAllTransactionsByUser() {
        String accountNumber = "123456";

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", "CREDIT", new BigDecimal("100.00"), accountNumber, "SUCCESS", LocalDateTime.now()));
        transactions.add(new Transaction("2", "DEBIT", new BigDecimal("50.00"), accountNumber, "SUCCESS", LocalDateTime.now()));
        transactions.add(new Transaction("3", "TRANSFER", new BigDecimal("200.00"), accountNumber, "SUCCESS", LocalDateTime.now()));

        when(transactionRepository.findByAccountNumber(accountNumber)).thenReturn(transactions);

        TransactionReport report = reportingService.getAllTransactionsByUser(accountNumber);

        assertEquals(3, report.getTransactions().size());
        assertEquals(new BigDecimal("350.00"), report.getTotalAmount());
    }

    @Test
    void testGetAllCreditsByUser() {
        String accountNumber = "123456";

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", "CREDIT", new BigDecimal("100.00"), accountNumber, "SUCCESS", LocalDateTime.now()));

        when(transactionRepository.findByAccountNumberAndTransactionType(accountNumber, "CREDIT")).thenReturn(transactions);

        TransactionReport report = reportingService.getAllCreditsByUser(accountNumber);

        assertEquals(1, report.getTransactions().size());
        assertEquals(new BigDecimal("100.00"), report.getTotalAmount());
    }

    @Test
    void testGetAllDebitsByUser() {
        String accountNumber = "123456";

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", "DEBIT", new BigDecimal("50.00"), accountNumber, "SUCCESS", LocalDateTime.now()));

        when(transactionRepository.findByAccountNumberAndTransactionType(accountNumber, "DEBIT")).thenReturn(transactions);

        TransactionReport report = reportingService.getAllDebitsByUser(accountNumber);

        assertEquals(1, report.getTransactions().size());
        assertEquals(new BigDecimal("50.00"), report.getTotalAmount());
    }

    @Test
    void testGetAllFundTransfersByUser() {
        String accountNumber = "123456";

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", "TRANSFER", new BigDecimal("200.00"), accountNumber, "SUCCESS", LocalDateTime.now()));

        when(transactionRepository.findByAccountNumberAndTransactionType(accountNumber, "TRANSFER")).thenReturn(transactions);

        TransactionReport report = reportingService.getAllFundTransfersByUser(accountNumber);

        assertEquals(1, report.getTransactions().size());
        assertEquals(new BigDecimal("200.00"), report.getTotalAmount());
    }

    @Test
    void testGetUsersCreatedWithinTimeframe() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(30);
        LocalDateTime endDate = LocalDateTime.now();

        List<User> users = new ArrayList<>();
        // Populate users list with dummy data or mock data

        when(userRepository.findByCreatedAtBetween(startDate, endDate)).thenReturn(users);

        List<User> fetchedUsers = reportingService.getUsersCreatedWithinTimeframe(startDate, endDate);

        assertEquals(users.size(), fetchedUsers.size());
    }
}


