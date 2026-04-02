package com.nk.usermanagement.service.impl;

import com.nk.usermanagement.dto.request.UserRequest;
import com.nk.usermanagement.dto.response.UserResponse;
import com.nk.usermanagement.entity.User;
import com.nk.usermanagement.mapper.UserMapper;
import com.nk.usermanagement.repository.UserRepository;
import com.nk.usermanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse getUserById(Long id) {
        //return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        existingUser.setName(userRequest.getName());
        existingUser.setEmail(userRequest.getEmail());
        User savedUpdatedUser = userRepository.save(existingUser);
        return userMapper.toResponse(savedUpdatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
