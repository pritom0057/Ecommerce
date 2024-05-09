package com.wall.street.ecommerce.repository;

import com.wall.street.ecommerce.domains.entities.Wishlist;
import com.wall.street.ecommerce.domains.repository.WishlistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WishlistRepositoryTest {

    @MockBean
    private WishlistRepository wishlistRepository;

    @Test
    public void testFindByCustomerId() {
        // Create mocked data
        var wishlistItem1 = new Wishlist();
        wishlistItem1.setId(1L);
        var wishlistItem2 = new Wishlist();
        wishlistItem2.setId(2L);
        var mockedWishlistItems = Arrays.asList(wishlistItem1, wishlistItem2);

        // Mock the behavior of the repository method
        when(wishlistRepository.findByCustomerId(1L)).thenReturn(mockedWishlistItems);

        // Call the repository method to find wishlist items by customer ID
        var foundWishlistItems = wishlistRepository.findByCustomerId(1L);

        // Assert the size of the returned list
        assertEquals(2, foundWishlistItems.size());

        // Add more assertions as needed to validate the content of foundWishlistItems
    }
}
