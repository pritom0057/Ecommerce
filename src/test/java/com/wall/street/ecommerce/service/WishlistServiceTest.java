package com.wall.street.ecommerce.service;

import com.wall.street.ecommerce.domains.dtos.WishlistDTO;
import com.wall.street.ecommerce.domains.entities.Wishlist;
import com.wall.street.ecommerce.domains.repository.WishlistRepository;
import com.wall.street.ecommerce.mappers.WishlistMapper;
import com.wall.street.ecommerce.services.implementation.WishlistServiceImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WishlistServiceTest {

    @MockBean
    private WishlistRepository wishlistRepository;

    @MockBean
    private WishlistMapper wishlistMapper;

    @Test
    public void testGetWishlistByCustomerId() {
        // Create mocked data
        var wishlistItem1 = new Wishlist();
        wishlistItem1.setId(1L);
        var wishlistItem2 = new Wishlist();
        wishlistItem2.setId(2L);
        var mockedWishlistItems = Arrays.asList(wishlistItem1, wishlistItem2);

        // Mock the behavior of wishlistRepository
        when(wishlistRepository.findByCustomerId(1L)).thenReturn(mockedWishlistItems);

        // Mock the behavior of wishlistMapper
        var wishlistDTO1 = new WishlistDTO();
        wishlistDTO1.setProductId(1L);
        var wishlistDTO2 = new WishlistDTO();
        wishlistDTO2.setProductId(2L);
        when(wishlistMapper.toWishlistDTO(wishlistItem1)).thenReturn(wishlistDTO1);
        when(wishlistMapper.toWishlistDTO(wishlistItem2)).thenReturn(wishlistDTO2);

        // Create instance of WishlistServiceImplementation
        var wishlistService = new WishlistServiceImplementation(wishlistRepository, wishlistMapper);

        // Call the service method to get wishlist items
        var wishlistDTOs = wishlistService.getWishlistByCustomerId(1L);

        // Verify that the repository method was called with the correct parameter
        verify(wishlistRepository, times(1)).findByCustomerId(1L);

        // Assert the size of the returned wishlistDTOs list
        assertEquals(2, wishlistDTOs.size());

        // Add more assertions as needed to validate the content of wishlistDTOs
    }
}

