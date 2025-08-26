package com.boardly.application.usecase;

import com.boardly.adapters.in.web.dto.auth.AuthenticationRequest;
import com.boardly.adapters.in.web.dto.auth.AuthenticationResponse;
import com.boardly.adapters.in.web.dto.auth.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
}
