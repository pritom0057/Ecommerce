package com.wall.street.ecommerce.controllers;

import com.wall.street.ecommerce.domains.dtos.WishlistDTO;
import com.wall.street.ecommerce.services.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<WishlistDTO>> getWishlistByCustomerId(@PathVariable Long customerId) {
        try {
            List<WishlistDTO> wishlist = wishlistService.getWishlistByCustomerId(customerId);
            log.info("Successfully retrieved wishlist for customer with ID {}: {}", customerId, wishlist);
            return ResponseEntity.ok(wishlist);
        } catch (Exception e) {
            log.error("Failed to get wishlist for customer with ID " + customerId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
