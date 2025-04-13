package com.adityapdev.ChaChing_api.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJWTService {
    String generateToken(String username);
    String extractUsername(String token);
    boolean validateToken(String token, UserDetails userDetails);
}
