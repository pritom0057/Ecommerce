package com.wall.street.ecommerce.services;

import com.wall.street.ecommerce.domains.dtos.WishlistDTO;

import java.util.List;

public interface WishlistService {
    List<WishlistDTO> getWishlistByCustomerId(Long customerId);
}
