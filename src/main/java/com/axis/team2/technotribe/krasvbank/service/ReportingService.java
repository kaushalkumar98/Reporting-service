package com.axis.team2.technotribe.krasvbank.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.team2.technotribe.krasvbank.dto.TransactionReport;
import com.axis.team2.technotribe.krasvbank.entity.Transaction;
import com.axis.team2.technotribe.krasvbank.entity.User;
import com.axis.team2.technotribe.krasvbank.repository.TransactionRepository;
import com.axis.team2.technotribe.krasvbank.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
public class ReportingService {

 @Autowired
 private TransactionRepository transactionRepository;
 
 @Autowired
 private UserRepository userRepository;

 //USER
 
 // Method to fetch all transactions by user within time frame 
 public TransactionReport getTransactionHistory(String accountNumber, LocalDateTime startDate, LocalDateTime endDate) {
	 List<Transaction> transactions = transactionRepository.findByAccountNumberAndCreatedAtBetween(accountNumber, startDate, endDate);
     BigDecimal totalAmount = transactions.stream()
                                          .map(Transaction::getAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);
	 return new TransactionReport(transactions, totalAmount);
 }
 
 // Method to fetch all transactions by user
 public TransactionReport getAllTransactions(String accountNumber) {
	 List<Transaction> transactions = transactionRepository.findByAccountNumber(accountNumber);
     BigDecimal totalAmount = transactions.stream()
                                          .map(Transaction::getAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);
	 return new TransactionReport(transactions, totalAmount);
 }

 //Method to fetch all credits by user
 public TransactionReport getAllCredits(String accountNumber) {
	 List<Transaction> transactions = transactionRepository.findByAccountNumberAndTransactionType(accountNumber, "CREDIT");
     BigDecimal totalAmount = transactions.stream()
                                          .map(Transaction::getAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);
     return new TransactionReport(transactions, totalAmount);
 }

 // Method to fetch all debits by user
 public TransactionReport getAllDebits(String accountNumber) {
	 List<Transaction> transactions = transactionRepository.findByAccountNumberAndTransactionType(accountNumber, "DEBIT");
     BigDecimal totalAmount = transactions.stream()
                                          .map(Transaction::getAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);
     return new TransactionReport(transactions, totalAmount);
 }

 //Method to fetch all transfers by user
 public TransactionReport getAllFundTransfers(String accountNumber) {
	 List<Transaction> transactions = transactionRepository.findByAccountNumberAndTransactionType(accountNumber, "TRANSFER");
     BigDecimal totalAmount = transactions.stream()
                                          .map(Transaction::getAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);
     return new TransactionReport(transactions, totalAmount);
 }
 
 // Method to fetch all Credits for Current Month
 public TransactionReport getAllCreditsForCurrentMonth(String accountNumber) {
     YearMonth currentMonth = YearMonth.now();
     LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
     LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);

     List<Transaction> transactions = transactionRepository.findByAccountNumberAndTransactionTypeAndCreatedAtBetween(accountNumber, "CREDIT", startOfMonth, endOfMonth);
     BigDecimal totalAmount = transactions.stream()
                                          .map(Transaction::getAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);

     return new TransactionReport(transactions, totalAmount);
 }
 
 // Method to fetch all Debits for Current Month
 public TransactionReport getAllDebitsForCurrentMonth(String accountNumber) {
     YearMonth currentMonth = YearMonth.now();
     LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
     LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);

     List<Transaction> transactions = transactionRepository.findByAccountNumberAndTransactionTypeAndCreatedAtBetween(accountNumber, "DEBIT", startOfMonth, endOfMonth);
     BigDecimal totalAmount = transactions.stream()
                                          .map(Transaction::getAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);

     return new TransactionReport(transactions, totalAmount);
 }

 
 //ADMIN
 
//Method to fetch all transactions within a specified time frame for all users
 public TransactionReport getAllTransactionsWithinTimeframe(LocalDateTime startDate, LocalDateTime endDate) {
	 List<Transaction> transactions = transactionRepository.findByCreatedAtBetween(startDate, endDate);
     BigDecimal totalAmount = transactions.stream()
                                          .map(Transaction::getAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);

     return new TransactionReport(transactions, totalAmount);
 }
 
//Method to fetch all transactions within a specified timeframe for a specific user
 public TransactionReport getTransactionsByUserWithinTimeframe(String accountNumber, LocalDateTime startDate, LocalDateTime endDate) {
	 List<Transaction> transactions = transactionRepository.findByAccountNumberAndCreatedAtBetween(accountNumber, startDate, endDate);
     BigDecimal totalAmount = transactions.stream()
                                          .map(Transaction::getAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);

     return new TransactionReport(transactions, totalAmount);
 }

 // Method to fetch all transactions for a specific user
 public TransactionReport getAllTransactionsByUser(String accountNumber) {
	 List<Transaction> transactions = transactionRepository.findByAccountNumber(accountNumber);
     BigDecimal totalAmount = transactions.stream()
                                          .map(Transaction::getAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);

     return new TransactionReport(transactions, totalAmount);
 }

 // Method to fetch all credits for a specific user
 public TransactionReport getAllCreditsByUser(String accountNumber) {
	 List<Transaction> transactions = transactionRepository.findByAccountNumberAndTransactionType(accountNumber, "CREDIT");
     BigDecimal totalAmount = transactions.stream()
                                          .map(Transaction::getAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);

     return new TransactionReport(transactions, totalAmount);
 }

 // Method to fetch all debits for a specific user
 public TransactionReport getAllDebitsByUser(String accountNumber) {
	 List<Transaction> transactions = transactionRepository.findByAccountNumberAndTransactionType(accountNumber, "DEBIT");
     BigDecimal totalAmount = transactions.stream()
                                          .map(Transaction::getAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);

     return new TransactionReport(transactions, totalAmount);
 }

 // Method to fetch all fund transfers for a specific user
 public TransactionReport getAllFundTransfersByUser(String accountNumber) {
	 List<Transaction> transactions = transactionRepository.findByAccountNumberAndTransactionType(accountNumber, "TRANSFER");
     BigDecimal totalAmount = transactions.stream()
                                          .map(Transaction::getAmount)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);

     return new TransactionReport(transactions, totalAmount);
 }
 
//Method to fetch users created within a specified timeframe
 public List<User> getUsersCreatedWithinTimeframe(LocalDateTime startDate, LocalDateTime endDate) {
     return userRepository.findByCreatedAtBetween(startDate, endDate);
 }
}

