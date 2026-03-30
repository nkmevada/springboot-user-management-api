package com.nk.usermanagement.service.impl;

import com.nk.usermanagement.entity.User;
import com.nk.usermanagement.repository.UserRepository;
import com.nk.usermanagement.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
