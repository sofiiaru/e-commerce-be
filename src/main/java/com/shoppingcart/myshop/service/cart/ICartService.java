package com.shoppingcart.myshop.service.cart;

import com.shoppingcart.myshop.model.Cart;
import com.shoppingcart.myshop.model.User;

import java.math.BigDecimal;

public interface ICartService {

    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
