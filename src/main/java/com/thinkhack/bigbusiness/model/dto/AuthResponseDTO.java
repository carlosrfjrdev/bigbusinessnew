package com.thinkhack.bigbusiness.model.dto;

import org.springframework.security.core.userdetails.UserDetails;

public record AuthResponseDTO(UserDetails user, String token) {
}
