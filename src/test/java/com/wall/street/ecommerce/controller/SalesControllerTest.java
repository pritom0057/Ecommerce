package com.wall.street.ecommerce.controller;

import com.wall.street.ecommerce.controllers.SalesController;
import com.wall.street.ecommerce.services.SalesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SalesController.class)
public class SalesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalesService salesService;

    @Test
    public void testGetTotalSaleAmountForCurrentDay_Success() throws Exception {
        // Mocked data
        var totalSaleAmount = BigDecimal.valueOf(1000);
        when(salesService.getTotalSaleAmountForCurrentDay()).thenReturn(totalSaleAmount);

        // Perform GET request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/sales/total-amount")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.content().string("1000"));

        // Verify that the service method was called
        verify(salesService, times(1)).getTotalSaleAmountForCurrentDay();
    }

    @Test
    public void testGetMaxSaleDay_Success() throws Exception {
        // Mocked data
        var maxSaleDay = LocalDateTime.of(2024, 5, 9, 0, 0);
        when(salesService.getMaxSaleDay(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(LocalDate.from(maxSaleDay));

        // Perform GET request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/sales/max-sale-day")
                                              .param("startDateTime", "2024-05-01T00:00:00")
                                              .param("endDateTime", "2024-05-31T23:59:59")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.content().string("\"2024-05-09\""));

        // Verify that the service method was called with the correct parameters
        verify(salesService, times(1)).getMaxSaleDay(any(LocalDateTime.class), any(LocalDateTime.class));
    }
}

