package com.nk.usermanagement.mapper;

import com.nk.usermanagement.dto.response.UserResponse;
import com.nk.usermanagement.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(User user){
        return UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
