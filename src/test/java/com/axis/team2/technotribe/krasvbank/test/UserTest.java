package com.axis.team2.technotribe.krasvbank.test;

import org.junit.jupiter.api.Test;

import com.axis.team2.technotribe.krasvbank.entity.User;
import com.axis.team2.technotribe.krasvbank.entity.UserRole;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

 class UserTest {

    @Test
    void testUserEntity() {
        // Test builder
        User user = User.builder()
                .id(1L)
                .name("John Doe")
                .gender("Male")
                .address("123 Main St")
                .stateOfOrigin("State")
                .accountNumber("123456789")
                .accountBalance(BigDecimal.valueOf(1000.00))
                .email("john.doe@example.com")
                .password("password")
                .phoneNumber("123-456-7890")
                .alternativePhoneNumber("098-765-4321")
                /*.status("Active")
                .role(UserRole.ROLE_USER)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())*/
                .build();

        // Test getters
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("Male", user.getGender());
        assertEquals("123 Main St", user.getAddress());
        assertEquals("State", user.getStateOfOrigin());
        assertEquals("123456789", user.getAccountNumber());
        assertEquals(BigDecimal.valueOf(1000.00), user.getAccountBalance());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("123-456-7890", user.getPhoneNumber());
        assertEquals("098-765-4321", user.getAlternativePhoneNumber());
		/*
		 * assertEquals("Active", user.getStatus()); assertEquals(UserRole.ROLE_USER,
		 * user.getRole()); assertNotNull(user.getCreatedAt());
		 * assertNotNull(user.getModifiedAt());
		 */

        // Test setters
        user.setId(2L);
        user.setName("Jane Doe");
        user.setGender("Female");
        user.setAddress("456 Another St");
        user.setStateOfOrigin("Another State");
        user.setAccountNumber("987654321");
        user.setAccountBalance(BigDecimal.valueOf(2000.00));
        user.setEmail("jane.doe@example.com");
        user.setPassword("newpassword");
        user.setPhoneNumber("321-654-0987");
        user.setAlternativePhoneNumber("876-543-2109");
		/*
		 * user.setStatus("Inactive"); user.setRole(UserRole.ROLE_ADMIN); LocalDateTime
		 * now = LocalDateTime.now(); user.setCreatedAt(now); user.setModifiedAt(now);
		 */

        // Verify updated values
        assertEquals(2L, user.getId());
        assertEquals("Jane Doe", user.getName());
        assertEquals("Female", user.getGender());
        assertEquals("456 Another St", user.getAddress());
        assertEquals("Another State", user.getStateOfOrigin());
        assertEquals("987654321", user.getAccountNumber());
        assertEquals(BigDecimal.valueOf(2000.00), user.getAccountBalance());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("newpassword", user.getPassword());
        assertEquals("321-654-0987", user.getPhoneNumber());
        assertEquals("876-543-2109", user.getAlternativePhoneNumber());
		/*
		 * assertEquals("Inactive", user.getStatus()); assertEquals(UserRole.ROLE_ADMIN,
		 * user.getRole()); assertEquals(now, user.getCreatedAt()); assertEquals(now,
		 * user.getModifiedAt());
		 */
    }
}

