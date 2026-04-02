package com.nk.usermanagement.service;

import com.nk.usermanagement.dto.request.UserRequest;
import com.nk.usermanagement.dto.response.UserResponse;
import com.nk.usermanagement.entity.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    List<User> getAllUser();
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id,UserRequest userRequest);
    void deleteUser(Long id);
}
