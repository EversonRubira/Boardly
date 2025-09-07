package com.boardly.application.auth;

import com.boardly.application.usecase.AuthService;
import com.boardly.adapters.in.web.dto.auth.AuthenticationRequest;
import com.boardly.adapters.in.web.dto.auth.AuthenticationResponse;
import com.boardly.adapters.in.web.dto.auth.RegisterRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name="app.security.mode", havingValue="jwt")
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
