package com.shoppingcart.myshop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CartDto {
    private String id;
    private Set<CartItemDto> items;
    private BigDecimal total;
}
