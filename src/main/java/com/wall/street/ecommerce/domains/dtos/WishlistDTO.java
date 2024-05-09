package com.wall.street.ecommerce.domains.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WishlistDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
}
