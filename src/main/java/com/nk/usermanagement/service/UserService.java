package com.nk.usermanagement.service;

import com.nk.usermanagement.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUser();
    User getUserById(Long id);

}
