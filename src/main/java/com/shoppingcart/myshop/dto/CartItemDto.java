package com.shoppingcart.myshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private String id;
    private BigDecimal unitPrice;
    private Integer quantity;
    private ProductDto product;
}
