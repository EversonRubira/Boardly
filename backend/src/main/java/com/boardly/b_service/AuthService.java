package com.boardly.b_service;

import com.boardly.b_service.jwt.JwtUtil;
import com.boardly.d_repository.UserRepository;
import com.boardly.e_model.User;
import com.boardly.f_dto.auth.AuthRequest;
import com.boardly.f_dto.auth.AuthResponse;
import com.boardly.f_dto.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .avatarUrl(request.getAvatarUrl())
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        //gerar o token na resposta
        String token = jwtUtil.generateToken(user);

        //retorna o token da resposta
        return AuthResponse.builder()
                .token(token)
                .build();
    }

   
}
