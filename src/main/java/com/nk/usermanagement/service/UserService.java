package com.nk.usermanagement.service;

import com.nk.usermanagement.dto.request.UserRequest;
import com.nk.usermanagement.dto.response.UserResponse;
import com.nk.usermanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    List<User> getAllUser();
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id,UserRequest userRequest);
    void deleteUser(Long id);
    List<UserResponse> getAllUser(Pageable pageable);
    List<UserResponse> searchUser(String keyword);
    List<UserResponse> getAllUser(Sort sort);
}
