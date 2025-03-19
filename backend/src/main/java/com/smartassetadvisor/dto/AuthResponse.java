package com.smartassetadvisor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor  // ✅ Automatically generates the constructor with all fields
public class AuthResponse {
    private String token;
    private String message;
}
