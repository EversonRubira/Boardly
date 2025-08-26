package com.boardly.application.service;

import com.boardly.application.usecase.AuthService;
import com.boardly.adapters.in.web.dto.auth.AuthenticationRequest;
import com.boardly.adapters.in.web.dto.auth.AuthenticationResponse;
import com.boardly.adapters.in.web.dto.auth.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        // implementar depois
        return null;
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        // implementar depois
        return null;
    }
}
