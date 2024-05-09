package com.wall.street.ecommerce.controller;

import com.wall.street.ecommerce.controllers.WishlistController;
import com.wall.street.ecommerce.domains.dtos.WishlistDTO;
import com.wall.street.ecommerce.services.WishlistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WishlistController.class)
public class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishlistService wishlistService;

    @Test
    public void testGetWishlistByCustomerId_Success() throws Exception {
        // Mocked data
        var wishlistItem = new WishlistDTO();
        wishlistItem.setProductId(1L);
        when(wishlistService.getWishlistByCustomerId(1L)).thenReturn(Collections.singletonList(wishlistItem));

        // Perform GET request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/wishlist/1")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].productId").value(1));

        // Verify that the service method was called with the correct parameter
        verify(wishlistService, times(1)).getWishlistByCustomerId(1L);
    }

    @Test
    public void testGetWishlistByCustomerId_InternalServerError() throws Exception {
        // Mock service to throw exception
        when(wishlistService.getWishlistByCustomerId(1L)).thenThrow(new RuntimeException("Test Exception"));

        // Perform GET request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/wishlist/1")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isInternalServerError());

        // Verify that the service method was called with the correct parameter
        verify(wishlistService, times(1)).getWishlistByCustomerId(1L);
    }
}

