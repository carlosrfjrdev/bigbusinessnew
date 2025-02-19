package com.thinkhack.bigbusiness.model.dto;

import com.thinkhack.bigbusiness.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewUserDTO(
        @NotBlank
        String username,
        @NotNull
        String nome,
        @NotNull
        String email,
        @NotNull
        UserRole role,
        @NotNull
        String password
) {
}
