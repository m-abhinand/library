package com.example.library.controller;

import com.example.library.config.JwtUtil;
import com.example.library.dto.LoginRequest;
import com.example.library.service.UserEntity;
import com.example.library.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Map;

@RestController
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }


        String token = jwtUtil.generate(user.getUsername(), user.getRole());

        return Map.of("token", token);
    }
}
