package com.thinkhack.bigbusiness.dto;

import org.springframework.security.core.userdetails.UserDetails;

public record SigninResponseDTO(UserDetails user, String token) {
}
