package com.wall.street.ecommerce.domains.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopSellingItemDTO {
    private long productId;
    private String productName;
    private BigDecimal totalSold;
}
