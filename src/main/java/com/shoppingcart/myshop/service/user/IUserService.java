package com.shoppingcart.myshop.service.user;

import com.shoppingcart.myshop.dto.UserDto;
import com.shoppingcart.myshop.model.User;
import com.shoppingcart.myshop.request.CreateUserRequest;
import com.shoppingcart.myshop.request.UpdateUserRequest;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertToDto(User user);

    User getAuthenticatedUser();
}
