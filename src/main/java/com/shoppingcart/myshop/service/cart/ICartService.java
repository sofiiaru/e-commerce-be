package com.shoppingcart.myshop.service.cart;

import com.shoppingcart.myshop.model.Cart;

import java.math.BigDecimal;

public interface ICartService {

    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
    Long initializeNewCart();

    Cart getCartByUserId(Long userId);
}
