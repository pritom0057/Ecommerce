package com.wall.street.ecommerce.services.implementation;


import com.wall.street.ecommerce.domains.repository.OrderRepository;
import com.wall.street.ecommerce.services.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class SalesServiceImplementation implements SalesService {

    private final OrderRepository orderRepository;

    @Override
    public BigDecimal getTotalSaleAmountForCurrentDay() {
        try {
            var currentDate = LocalDate.now();
            var startOfDay = currentDate.atStartOfDay();
            var endOfDay = currentDate.atTime(LocalTime.MAX);

            BigDecimal totalSaleAmount = orderRepository.getTotalSaleAmountForCurrentDay(startOfDay, endOfDay);
            log.info("Successfully retrieved total sale amount for current day: {}", totalSaleAmount);
            return totalSaleAmount;
        } catch (Exception e) {
            log.error("Failed to retrieve total sale amount for current day", e);
            throw e; // Propagate the exception
        }
    }

    @Override
    public LocalDate getMaxSaleDay(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        try {
            LocalDate maxSaleDay = orderRepository.findMaxSaleDay(startDateTime, endDateTime);
            log.info("Successfully retrieved max sale day between {} and {}: {}", startDateTime, endDateTime, maxSaleDay);
            return maxSaleDay;
        } catch (Exception e) {
            log.error("Failed to retrieve max sale day between {} and {}", startDateTime, endDateTime, e);
            throw e; // Propagate the exception
        }
    }
}

