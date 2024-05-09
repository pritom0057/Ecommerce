package com.wall.street.ecommerce.services.implementation;

import com.wall.street.ecommerce.domains.dtos.WishlistDTO;
import com.wall.street.ecommerce.domains.entities.Wishlist;
import com.wall.street.ecommerce.domains.repository.WishlistRepository;
import com.wall.street.ecommerce.mappers.WishlistMapper;
import com.wall.street.ecommerce.services.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class WishlistServiceImplementation implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final WishlistMapper wishlistMapper;

    @Override
    public List<WishlistDTO> getWishlistByCustomerId(Long customerId) {
        try {
            List<Wishlist> wishlistItems = wishlistRepository.findByCustomerId(customerId);
            List<WishlistDTO> wishlistDTOs = wishlistItems.stream()
                                                          .map(wishlistMapper::toWishlistDTO)
                                                          .collect(Collectors.toList());
            log.info("Successfully retrieved wishlist for customer with ID {}: {}", customerId, wishlistDTOs);
            return wishlistDTOs;
        } catch (Exception e) {
            log.error("Failed to retrieve wishlist for customer with ID " + customerId, e);
            throw e; // Propagate the exception
        }
    }
}
