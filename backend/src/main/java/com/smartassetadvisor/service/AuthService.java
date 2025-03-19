package com.smartassetadvisor.service;

import com.smartassetadvisor.dto.AuthRequest;
import com.smartassetadvisor.dto.AuthResponse;
import com.smartassetadvisor.model.User;
import com.smartassetadvisor.repository.UserRepository;
import com.smartassetadvisor.config.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService { 

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager; // No direct injection

    @Autowired
    public void setAuthenticationManager(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Load User by Username (For Authentication)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    // ✅ Register User
    public AuthResponse register(AuthRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encrypt password
        user.setAge(request.getAge());
        user.setOccupation(request.getOccupation());
        user.setAnnualIncome(Double.parseDouble(request.getAnnualIncome())); // ✅ Convert String to Double
        user.setRiskCategory(request.getRiskCategory());
        user.setInvestmentGoals(request.getInvestmentGoals());

        userRepository.save(user);

        // Generate JWT Token
        UserDetails userDetails = loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse(token, "User registered successfully!");
    }

    // ✅ Login User
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse(token, "Login successful!");
    }
}
