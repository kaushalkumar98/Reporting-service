package com.axis.team2.technotribe.krasvbank.repository;

import java.time.LocalDateTime;
import java.util.List;

//UserRepository.java

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.team2.technotribe.krasvbank.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
 User findByAccountNumber(String accountNumber);
 
//Method to find users by creation timestamp between two dates
 List<User> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}

