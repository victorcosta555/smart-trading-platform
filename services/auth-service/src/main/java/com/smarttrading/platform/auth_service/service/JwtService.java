package com.smarttrading.platform.auth_service.service;

import com.smarttrading.platform.auth_service.entity.Role;
import com.smarttrading.platform.auth_service.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles",
                        user.getRoles()
                                .stream()
                                .map(Role::getName)
                                .toList())
                .setIssuedAt(new Date())
                .setExpiration(new Date(
                        System.currentTimeMillis() + 3600000))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
}
