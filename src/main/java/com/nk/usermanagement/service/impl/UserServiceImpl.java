package com.nk.usermanagement.service.impl;

import com.nk.usermanagement.dto.request.UserRequest;
import com.nk.usermanagement.dto.response.UserResponse;
import com.nk.usermanagement.entity.User;
import com.nk.usermanagement.exception.ResourceNotFoundException;
import com.nk.usermanagement.mapper.UserMapper;
import com.nk.usermanagement.repository.UserRepository;
import com.nk.usermanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
        logger.info("Creating user with email: {}", userRequest.getEmail());
        return userMapper.toResponse(savedUser);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse getUserById(Long id) {
        //return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id :" + id));
        logger.info("Get user data with id: {}",id);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        existingUser.setName(userRequest.getName());
        existingUser.setEmail(userRequest.getEmail());
        User savedUpdatedUser = userRepository.save(existingUser);
        logger.info("Update user data with id: {}",id);
        return userMapper.toResponse(savedUpdatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        logger.info("Delete user data with id: {}",id);
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> getAllUser(Pageable pageable) {
        Page<User> userPage;
        userPage = userRepository.findAll(pageable);
       // return userPage.map(userMapper::toResponse);
        return userPage.getContent()   // 👈 only data
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContainingIgnoreCase(keyword);
        logger.info("Search username: {}",keyword);
        return users.stream().map(userMapper::toResponse).toList();
    }

    @Override
    public List<UserResponse> getAllUser(Sort sort) {
        List<User> users = userRepository.findAll(sort);
        return users.stream().map(userMapper::toResponse).toList();
    }
}
