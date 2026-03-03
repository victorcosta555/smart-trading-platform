package com.smarttrading.platform.auth_service.service;

import com.smarttrading.platform.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(String username, String password) {

        var user = userRepository
                .findByUsername(username)
                .orElseThrow();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }

        return jwtService.generateToken(user);
    }
}
