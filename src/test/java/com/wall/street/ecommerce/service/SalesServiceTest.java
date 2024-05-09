package com.wall.street.ecommerce.service;

import com.wall.street.ecommerce.domains.repository.OrderRepository;
import com.wall.street.ecommerce.services.SalesService;
import com.wall.street.ecommerce.services.implementation.SalesServiceImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SalesServiceTest {

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testGetTotalSaleAmountForCurrentDay() {
        // Mock data
        var expectedTotalSaleAmount = BigDecimal.valueOf(1000);
        var currentDate = LocalDate.now();
        var startOfDay = currentDate.atStartOfDay();
        var endOfDay = currentDate.atTime(LocalTime.MAX);

        // Mock repository method
        when(orderRepository.getTotalSaleAmountForCurrentDay(startOfDay, endOfDay)).thenReturn(expectedTotalSaleAmount);

        SalesService salesService = new SalesServiceImplementation(orderRepository);

        // Call the service method
        var actualTotalSaleAmount = salesService.getTotalSaleAmountForCurrentDay();

        // Verify that the repository method was called with the correct parameter
        verify(orderRepository, times(1)).getTotalSaleAmountForCurrentDay(startOfDay, endOfDay);

        // Assert the result
        assertEquals(expectedTotalSaleAmount, actualTotalSaleAmount);
    }

    @Test
    public void testGetMaxSaleDay() {
        // Mock data
        var startDateTime = LocalDateTime.of(2024, 5, 1, 0, 0);
        var endDateTime = LocalDateTime.of(2024, 5, 31, 23, 59, 59);
        var expectedMaxSaleDay = LocalDate.of(2024, 5, 9);

        // Mock repository method
        when(orderRepository.findMaxSaleDay(startDateTime, endDateTime)).thenReturn(expectedMaxSaleDay);

        SalesService salesService = new SalesServiceImplementation(orderRepository);
        // Call the service method
        var actualMaxSaleDay = salesService.getMaxSaleDay(startDateTime, endDateTime);

        // Verify that the repository method was called with the correct parameter
        verify(orderRepository, times(1)).findMaxSaleDay(startDateTime, endDateTime);

        // Assert the result
        assertEquals(expectedMaxSaleDay, actualMaxSaleDay);
    }
}
