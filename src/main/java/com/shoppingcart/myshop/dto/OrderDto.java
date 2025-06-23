package com.shoppingcart.myshop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {
    private Long orderId;
    private Long userId;
    private LocalDate orderDate;
    private String orderStatus;
    private BigDecimal orderTotal;
    private List<OrderItemDto> items;

}
