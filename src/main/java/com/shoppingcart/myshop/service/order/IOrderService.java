package com.shoppingcart.myshop.service.order;

import com.shoppingcart.myshop.dto.OrderDto;
import com.shoppingcart.myshop.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    Order getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);
}
