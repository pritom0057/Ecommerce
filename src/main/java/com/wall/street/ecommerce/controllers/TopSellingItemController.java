package com.wall.street.ecommerce.controllers;

import com.wall.street.ecommerce.domains.dtos.TopSellingItemDTO;
import com.wall.street.ecommerce.services.TopSellingItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/top-selling-items")
public class TopSellingItemController {

    private final TopSellingItemService topSellingItemService;

    @GetMapping("/top5-by-sale-amount")
    public ResponseEntity<List<TopSellingItemDTO>> getTop5SellingItems() {
        try {
            var topSellingItems = topSellingItemService.getTop5SellingItems();
            log.info("Successfully retrieved top 5 selling items by sale amount: {}", topSellingItems);
            return ResponseEntity.ok(topSellingItems);
        } catch (Exception e) {
            log.error("Failed to retrieve top 5 selling items by sale amount", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/top5-by-number-of-sale-last-month")
    public ResponseEntity<List<TopSellingItemDTO>> getTop5SellingItemsLastMonth() {
        try {
            var topSellingItems = topSellingItemService.getTop5SellingItemsLastMonth();
            log.info("Successfully retrieved top 5 selling items by sale amount last month: {}", topSellingItems);
            return ResponseEntity.ok(topSellingItems);
        } catch (Exception e) {
            log.error("Failed to retrieve top 5 selling items by sale amount last month", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
