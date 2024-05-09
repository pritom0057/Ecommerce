package com.wall.street.ecommerce.controller;

import com.wall.street.ecommerce.controllers.TopSellingItemController;
import com.wall.street.ecommerce.domains.dtos.TopSellingItemDTO;
import com.wall.street.ecommerce.domains.entities.Product;
import com.wall.street.ecommerce.services.TopSellingItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TopSellingItemController.class)
public class TopSellingItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopSellingItemService topSellingItemService;

    @Test
    public void testGetTop5SellingItems_Success() throws Exception {
        // Mock data
        var product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");

        var product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");

        // Creating TopSellingItemDTO objects
        var topSellingItemDTO1 = new TopSellingItemDTO();
        topSellingItemDTO1.setProductId(product1.getId());
        topSellingItemDTO1.setProductName(product1.getName());
        topSellingItemDTO1.setTotalSold(BigDecimal.valueOf(100));

        var topSellingItemDTO2 = new TopSellingItemDTO();
        topSellingItemDTO2.setProductId(product2.getId());
        topSellingItemDTO2.setProductName(product2.getName());
        topSellingItemDTO2.setTotalSold(BigDecimal.valueOf(90));

        var topSellingItems = Arrays.asList(topSellingItemDTO1, topSellingItemDTO2);

        // Mock the behavior of topSellingItemService
        when(topSellingItemService.getTop5SellingItems()).thenReturn(topSellingItems);

        // Perform GET request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/top-selling-items/top5-by-sale-amount")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].productName").value("Product 1"))
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].productId").value(1))
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalSold").value(100))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].productName").value("Product 2"))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].productId").value(2))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].totalSold").value(90));

        // Verify that the service method was called
        verify(topSellingItemService, times(1)).getTop5SellingItems();
    }

    @Test
    public void testGetTop5SellingItemsLastMonth_Success() throws Exception {
        // Mock data
        var product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");

        var product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");

        // Creating TopSellingItemDTO objects
        var topSellingItemDTO1 = new TopSellingItemDTO();
        topSellingItemDTO1.setProductId(product1.getId());
        topSellingItemDTO1.setProductName(product1.getName());
        topSellingItemDTO1.setTotalSold(BigDecimal.valueOf(100));

        var topSellingItemDTO2 = new TopSellingItemDTO();
        topSellingItemDTO2.setProductId(product2.getId());
        topSellingItemDTO2.setProductName(product2.getName());
        topSellingItemDTO2.setTotalSold(BigDecimal.valueOf(90));

        var topSellingItems = Arrays.asList(topSellingItemDTO1, topSellingItemDTO2);

        // Mock the behavior of topSellingItemService
        when(topSellingItemService.getTop5SellingItemsLastMonth()).thenReturn(topSellingItems);

        // Perform GET request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/top-selling-items/top5-by-sale-amount-last-month")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].productName").value("Product 1"))
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].productId").value(1))
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalSold").value(100))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].productName").value("Product 2"))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].productId").value(2))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].totalSold").value(90));

        // Verify that the service method was called
        verify(topSellingItemService, times(1)).getTop5SellingItemsLastMonth();
    }

}


