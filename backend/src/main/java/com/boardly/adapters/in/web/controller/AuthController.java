package com.boardly.adapters.in.web.controller;

import com.boardly.adapters.in.web.dto.auth.AuthenticationRequest;
import com.boardly.adapters.in.web.dto.auth.AuthenticationResponse;
import com.boardly.adapters.in.web.dto.auth.RegisterRequest;
import com.boardly.application.usecase.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.security.mode", havingValue = "jwt")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
