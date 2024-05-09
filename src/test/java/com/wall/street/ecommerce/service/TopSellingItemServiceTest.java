package com.wall.street.ecommerce.service;

import com.wall.street.ecommerce.domains.repository.OrderRepository;
import com.wall.street.ecommerce.services.TopSellingItemService;
import com.wall.street.ecommerce.services.implementation.TopSellingItemServiceImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TopSellingItemServiceTest {

    @MockBean
    private OrderRepository orderRepository;


    @Test
    public void testGetTop5SellingItems_Success() {
        // Mocked data
        Object[] row1 = {1, "Product 1", BigDecimal.valueOf(100)};
        Object[] row2 = {2, "Product 2", BigDecimal.valueOf(90)};
        Object[] row3 = {3, "Product 3", BigDecimal.valueOf(80)};
        Object[] row4 = {4, "Product 4", BigDecimal.valueOf(70)};
        Object[] row5 = {5, "Product 5", BigDecimal.valueOf(60)};
        var rows = Arrays.asList(row1, row2, row3, row4, row5);

        // Mock the behavior of orderRepository
        when(orderRepository.findTop5SellingItems()).thenReturn(rows);

        TopSellingItemService topSellingItemService = new TopSellingItemServiceImplementation(orderRepository);
        // Call the service method
        var topSellingItems = topSellingItemService.getTop5SellingItems();

        // Verify that the repository method was called
        verify(orderRepository, times(1)).findTop5SellingItems();

        // Assert the result
        assertEquals(5, topSellingItems.size());
        assertEquals(1, topSellingItems.get(0).getProductId());
        assertEquals("Product 1", topSellingItems.get(0).getProductName());
        assertEquals(BigDecimal.valueOf(100), topSellingItems.get(0).getTotalSold());
        // Add more assertions as needed for other items
    }

    @Test
    public void testGetTop5SellingItemsLastMonth_Success() {
        // Mocked data
        Object[] row1 = {1, "Product 1", BigDecimal.valueOf(100)};
        Object[] row2 = {2, "Product 2", BigDecimal.valueOf(90)};
        Object[] row3 = {3, "Product 3", BigDecimal.valueOf(80)};
        Object[] row4 = {4, "Product 4", BigDecimal.valueOf(70)};
        Object[] row5 = {5, "Product 5", BigDecimal.valueOf(60)};
        var rows = Arrays.asList(row1, row2, row3, row4, row5);

        // Mock the behavior of orderRepository
        when(orderRepository.findTop5SellingLastMonth()).thenReturn(rows);

        TopSellingItemService topSellingItemService = new TopSellingItemServiceImplementation(orderRepository);

        // Call the service method
        var topSellingItems = topSellingItemService.getTop5SellingItemsLastMonth();

        // Verify that the repository method was called
        verify(orderRepository, times(1)).findTop5SellingLastMonth();

        // Assert the result
        assertEquals(5, topSellingItems.size());
        assertEquals(1, topSellingItems.get(0).getProductId());
        assertEquals("Product 1", topSellingItems.get(0).getProductName());
        assertEquals(BigDecimal.valueOf(100), topSellingItems.get(0).getTotalSold());
        // Add more assertions as needed for other items
    }
}

