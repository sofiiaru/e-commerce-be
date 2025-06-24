package com.shoppingcart.myshop.dto;

import com.shoppingcart.myshop.model.Cart;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<OrderDto> orders;
    private CartDto cart;

}
