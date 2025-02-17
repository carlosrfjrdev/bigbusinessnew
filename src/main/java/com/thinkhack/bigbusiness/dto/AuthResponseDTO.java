package com.thinkhack.bigbusiness.dto;

import org.springframework.security.core.userdetails.UserDetails;

public record AuthResponseDTO(UserDetails user, String token) {
}
