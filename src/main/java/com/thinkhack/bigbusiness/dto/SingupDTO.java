package com.thinkhack.bigbusiness.dto;

import com.thinkhack.bigbusiness.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.apache.catalina.User;

public record SingupDTO(
        @NotBlank
        String username,
        @NotNull
        String name,
        @NotNull
        String email,
        @NotNull
        UserRole role,
        @NotNull
        String password
) {
}
