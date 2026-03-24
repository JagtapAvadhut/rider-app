package com.rideshare.authservice.util;

import com.rideshare.common.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenService {
    private final JwtUtil jwtUtil;

    public JwtTokenService(@Value("${security.jwt.secret}") String secret,
                           @Value("${security.jwt.ttl-seconds:3600}") long ttl) {
        this.jwtUtil = new JwtUtil(secret, ttl);
    }

    public String generateToken(String subject, String role) {
        return jwtUtil.generateToken(subject, role);
    }
}
