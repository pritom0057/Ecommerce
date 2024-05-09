package com.wall.street.ecommerce.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SalesService {
    BigDecimal getTotalSaleAmountForCurrentDay();
    LocalDate getMaxSaleDay(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
