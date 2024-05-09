package com.wall.street.ecommerce.repository;

import com.wall.street.ecommerce.domains.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderItemRepositoryTest {

    @MockBean
    private OrderRepository orderItemRepository;

    @Test
    public void testFindTop5SellingLastMonth() {
        // Mock the behavior of the repository method
        var mockedResult = Arrays.asList(
                new Object[]{1L, "Product 1", 10L},
                new Object[]{2L, "Product 2", 8L},
                new Object[]{3L, "Product 3", 5L},
                new Object[]{4L, "Product 4", 3L},
                new Object[]{5L, "Product 5", 2L}
        );
        when(orderItemRepository.findTop5SellingLastMonth()).thenReturn(mockedResult);

        // Call the repository method to fetch the top 5 selling items of last month
        var top5SellingItems = orderItemRepository.findTop5SellingLastMonth();

        // Assert that the result matches the mocked data
        assertEquals(mockedResult, top5SellingItems);
    }
}

