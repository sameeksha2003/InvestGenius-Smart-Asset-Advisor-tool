package com.smartassetadvisor.config;

import com.smartassetadvisor.config.JwtUtil;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Component  // ✅ Fixed spelling mistake
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = extractToken(request);
        
        if (token != null) {
            try {
                System.out.println("🔹 Extracted Token: " + token);
    
                String username = jwtUtil.extractUsername(token);
                System.out.println("🔹 Extracted Username: " + username);
    
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                List<String> roles = jwtUtil.extractRoles(token);  
                System.out.println("🔹 Extracted Roles: " + roles); 
    
                if (jwtUtil.validateToken(token, userDetails)) {
                    System.out.println("✅ Token is Valid!");
    
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    System.out.println("❌ Invalid Token!");
                }
            } catch (Exception e) {
                System.out.println("⚠️ JWT Authentication Failed: " + e.getMessage());
            }
        } else {
            System.out.println("⚠️ No Token Found in Request!");
        }
    
        chain.doFilter(request, response);
    }
    


    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
