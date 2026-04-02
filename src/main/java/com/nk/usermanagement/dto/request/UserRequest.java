package com.nk.usermanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {
    @NotNull(message="Name required")
    private String name;

    @NotBlank
    private String email;
}
