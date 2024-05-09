package com.wall.street.ecommerce.services;

import com.wall.street.ecommerce.domains.dtos.TopSellingItemDTO;

import java.util.List;

public interface TopSellingItemService {
    List<TopSellingItemDTO> getTop5SellingItems();
    List<TopSellingItemDTO> getTop5SellingItemsLastMonth();
}
