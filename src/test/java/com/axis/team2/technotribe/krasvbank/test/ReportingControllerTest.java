package com.axis.team2.technotribe.krasvbank.test;

import com.axis.team2.technotribe.krasvbank.controller.ReportingController;
import com.axis.team2.technotribe.krasvbank.dto.TransactionReport;
import com.axis.team2.technotribe.krasvbank.entity.Transaction;
import com.axis.team2.technotribe.krasvbank.service.ReportingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportingController.class)
 class ReportingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportingService reportingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	/*
	 * @Test void testGetTransactionHistory() throws Exception {
	 * when(reportingService.getTransactionHistory(anyString(),
	 * any(LocalDateTime.class), any(LocalDateTime.class))) .thenReturn(new
	 * TransactionReport(null, null));
	 * 
	 * mockMvc.perform(get("/reports/transactions") .param("accountNumber",
	 * "123456789") .param("startDate", "2023-01-01T00:00:00") .param("endDate",
	 * "2023-12-31T23:59:59")) .andExpect(status().isOk()); }
	 */

    @Test
     void testGetAllTransactions() throws Exception {
        when(reportingService.getAllTransactions(anyString()))
                .thenReturn(new TransactionReport(null, null));

        mockMvc.perform(get("/reports/transactions/all")
                .param("accountNumber", "123456789"))
                .andExpect(status().isOk());
    }

    @Test
     void testGetAllCredits() throws Exception {
        when(reportingService.getAllCredits(anyString()))
                .thenReturn(new TransactionReport(null, null));

        mockMvc.perform(get("/reports/transactions/credits")
                .param("accountNumber", "123456789"))
                .andExpect(status().isOk());
    }

    @Test
     void testGetAllDebits() throws Exception {
        when(reportingService.getAllDebits(anyString()))
                .thenReturn(new TransactionReport(null, null));

        mockMvc.perform(get("/reports/transactions/debits")
                .param("accountNumber", "123456789"))
                .andExpect(status().isOk());
    }

    @Test
     void testGetAllFundTransfers() throws Exception {
        when(reportingService.getAllFundTransfers(anyString()))
                .thenReturn(new TransactionReport(null, null));

        mockMvc.perform(get("/reports/transactions/transfers")
                .param("accountNumber", "123456789"))
                .andExpect(status().isOk());
    }

    @Test
     void testGetAllCreditsForCurrentMonth() throws Exception {
        when(reportingService.getAllCreditsForCurrentMonth(anyString()))
                .thenReturn(new TransactionReport(null, null));

        mockMvc.perform(get("/reports/user/current-month-credits")
                .param("accountNumber", "123456789"))
                .andExpect(status().isOk());
    }

    @Test
     void testGetAllDebitsForCurrentMonth() throws Exception {
        when(reportingService.getAllDebitsForCurrentMonth(anyString()))
                .thenReturn(new TransactionReport(null, null));

        mockMvc.perform(get("/reports/user/current-month-debits")
                .param("accountNumber", "123456789"))
                .andExpect(status().isOk());
    }

	/*
	 * @Test void testGetAllTransactionsWithinTimeframe() throws Exception {
	 * when(reportingService.getAllTransactionsWithinTimeframe(any(LocalDateTime.
	 * class), any(LocalDateTime.class))) .thenReturn(new TransactionReport(null,
	 * null));
	 * 
	 * mockMvc.perform(get("/reports/admin/transactions") .param("startDate",
	 * "2023-01-01T00:00:00") .param("endDate", "2023-12-31T23:59:59"))
	 * .andExpect(status().isOk()); }
	 * 
	 * @Test void testGetTransactionsByUserWithinTimeframe() throws Exception {
	 * when(reportingService.getTransactionsByUserWithinTimeframe(anyString(),
	 * any(LocalDateTime.class), any(LocalDateTime.class))) .thenReturn(new
	 * TransactionReport(null, null));
	 * 
	 * mockMvc.perform(get("/reports/admin/user-transactions")
	 * .param("accountNumber", "123456789") .param("startDate",
	 * "2023-01-01T00:00:00") .param("endDate", "2023-12-31T23:59:59"))
	 * .andExpect(status().isOk()); }
	 */

    @Test
     void testGetAllTransactionsByUser() throws Exception {
        when(reportingService.getAllTransactionsByUser(anyString()))
                .thenReturn(new TransactionReport(null, null));

        mockMvc.perform(get("/reports/admin/user-all-transactions")
                .param("accountNumber", "123456789"))
                .andExpect(status().isOk());
    }

    @Test
     void testGetAllCreditsByUser() throws Exception {
        when(reportingService.getAllCreditsByUser(anyString()))
                .thenReturn(new TransactionReport(null, null));

        mockMvc.perform(get("/reports/admin/user-credits")
                .param("accountNumber", "123456789"))
                .andExpect(status().isOk());
    }

    @Test
     void testGetAllDebitsByUser() throws Exception {
        when(reportingService.getAllDebitsByUser(anyString()))
                .thenReturn(new TransactionReport(null, null));

        mockMvc.perform(get("/reports/admin/user-debits")
                .param("accountNumber", "123456789"))
                .andExpect(status().isOk());
    }

    @Test
     void testGetAllFundTransfersByUser() throws Exception {
        when(reportingService.getAllFundTransfersByUser(anyString()))
                .thenReturn(new TransactionReport(null, null));

        mockMvc.perform(get("/reports/admin/user-transfers")
                .param("accountNumber", "123456789"))
                .andExpect(status().isOk());
    }

	/*
	 * @Test void testGetUsersCreatedWithinTimeframe() throws Exception {
	 * when(reportingService.getUsersCreatedWithinTimeframe(any(LocalDateTime.class)
	 * , any(LocalDateTime.class))) .thenReturn(Collections.emptyList());
	 * 
	 * mockMvc.perform(get("/reports/admin/users-created") .param("startDate",
	 * "2023-01-01T00:00:00") .param("endDate", "2023-12-31T23:59:59"))
	 * .andExpect(status().isOk()); }
	 */
}
