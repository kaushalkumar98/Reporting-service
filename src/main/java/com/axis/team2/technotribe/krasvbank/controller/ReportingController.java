package com.axis.team2.technotribe.krasvbank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.axis.team2.technotribe.krasvbank.dto.TransactionReport;
import com.axis.team2.technotribe.krasvbank.entity.Transaction;
import com.axis.team2.technotribe.krasvbank.entity.User;
import com.axis.team2.technotribe.krasvbank.service.ReportingService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportingController {

 @Autowired
 private ReportingService reportingService;
 
//USER
 
 // fetch all transactions by user within a time frame
// @GetMapping("/transactions")
// public TransactionReport getTransactionHistory(
//         @RequestParam String accountNumber,
//         @RequestParam String startDate,
//         @RequestParam String endDate) {
//
//     LocalDateTime start = LocalDateTime.parse(startDate);
//     LocalDateTime end = LocalDateTime.parse(endDate);
//     return reportingService.getTransactionHistory(accountNumber, start, end);
// }
 
 // fetch all the transactions by user
 @GetMapping("/transactions/all")
 public TransactionReport getAllTransactions(@RequestParam String accountNumber) {
     return reportingService.getAllTransactions(accountNumber);
 }

 // fetch all the credits by user
 @GetMapping("/transactions/credits")
 public TransactionReport getAllCredits(@RequestParam String accountNumber) {
     return reportingService.getAllCredits(accountNumber);
 }

 //fetch all the debits by user
 @GetMapping("/transactions/debits")
 public TransactionReport getAllDebits(@RequestParam String accountNumber) {
     return reportingService.getAllDebits(accountNumber);
 }

 //fetch all the transfers by user
 @GetMapping("/transactions/transfers")
 public TransactionReport getAllFundTransfers(@RequestParam String accountNumber) {
     return reportingService.getAllFundTransfers(accountNumber);
 }
 
 //fetch all credits for current month
 @GetMapping("/user/current-month-credits")
 public TransactionReport getAllCreditsForCurrentMonth(@RequestParam String accountNumber) {
     return reportingService.getAllCreditsForCurrentMonth(accountNumber);
 }
 
 //fetch all debits for current month
 @GetMapping("/user/current-month-debits")
 public TransactionReport getAllDebitsForCurrentMonth(@RequestParam String accountNumber) {
     return reportingService.getAllDebitsForCurrentMonth(accountNumber);
 }
 
 //ADMIN
 
 //fetch all transactions within a specified timeframe for all users
// @GetMapping("/admin/transactions")
// public TransactionReport getAllTransactionsWithinTimeframe(
//         @RequestParam String startDate,
//         @RequestParam String endDate) {
//
//     LocalDateTime start = LocalDateTime.parse(startDate);
//     LocalDateTime end = LocalDateTime.parse(endDate);
//     return reportingService.getAllTransactionsWithinTimeframe(start, end);
// }
 
 // fetch all transactions within a specified timeframe for a specific user
// @GetMapping("/admin/user-transactions")
// public TransactionReport getTransactionsByUserWithinTimeframe(
//         @RequestParam String accountNumber,
//         @RequestParam String startDate,
//         @RequestParam String endDate) {
//
//     LocalDateTime start = LocalDateTime.parse(startDate);
//     LocalDateTime end = LocalDateTime.parse(endDate);
//     return reportingService.getTransactionsByUserWithinTimeframe(accountNumber, start, end);
// }
 
 //fetch all transactions for a specific user
 @GetMapping("/admin/user-all-transactions")
 public TransactionReport getAllTransactionsByUser(@RequestParam String accountNumber) {
     return reportingService.getAllTransactionsByUser(accountNumber);
 }
 
 //fetch all credits for a specific user
 @GetMapping("/admin/user-credits")
 public TransactionReport getAllCreditsByUser(@RequestParam String accountNumber) {
     return reportingService.getAllCreditsByUser(accountNumber);
 }
 
 // fetch all debits for a specific user
 @GetMapping("/admin/user-debits")
 public TransactionReport getAllDebitsByUser(@RequestParam String accountNumber) {
     return reportingService.getAllDebitsByUser(accountNumber);
 }
 
 //fetch all fund transfers for a specific user
 @GetMapping("/admin/user-transfers")
 public TransactionReport getAllFundTransfersByUser(@RequestParam String accountNumber) {
     return reportingService.getAllFundTransfersByUser(accountNumber);
 }
 
 //fetch all users created within a time-frame
// @GetMapping("/admin/users-created")
// public List<User> getUsersCreatedWithinTimeframe(
//         @RequestParam String startDate,
//         @RequestParam String endDate) {
//
//     LocalDateTime start = LocalDateTime.parse(startDate);
//     LocalDateTime end = LocalDateTime.parse(endDate);
//     return reportingService.getUsersCreatedWithinTimeframe(start, end);
// }
 
 
}

