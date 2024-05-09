package com.wall.street.ecommerce.controllers;

import com.wall.street.ecommerce.services.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @GetMapping("/total-amount")
    public ResponseEntity<BigDecimal> getTotalSaleAmountForCurrentDay() {
        try {
            var totalSaleAmount = salesService.getTotalSaleAmountForCurrentDay();
            log.info("Successfully retrieved total sale amount for current day: {}", totalSaleAmount);
            return ResponseEntity.ok(totalSaleAmount);
        } catch (Exception e) {
            log.error("An error occurred while retrieving total sale amount for current day", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/max-sale-day")
    public ResponseEntity<LocalDate> getMaxSaleDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {
        try {
            var maxSaleDay = salesService.getMaxSaleDay(startDateTime, endDateTime);
            log.info("Successfully retrieved max sale day between {} and {}: {}", startDateTime, endDateTime, maxSaleDay);
            return ResponseEntity.ok(maxSaleDay);
        } catch (Exception e) {
            log.error("An error occurred while retrieving max sale day between {} and {}", startDateTime, endDateTime, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
