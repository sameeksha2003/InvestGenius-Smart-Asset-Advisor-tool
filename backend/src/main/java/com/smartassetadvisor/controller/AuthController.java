package com.smartassetadvisor.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.*;
import com.smartassetadvisor.dto.UserDto;
import com.smartassetadvisor.model.User;
import com.smartassetadvisor.service.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody @Valid UserDto userDto) {
        User user = new User(null, userDto.getUsername(), userDto.getEmail(), userDto.getPassword(), null, null);
        String token = authService.signup(user);
        return Map.of("token", token, "email", user.getEmail());
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String token = authService.login(credentials.get("email"), credentials.get("password"));
        return Map.of("token", token);
    }
}
