package com.boardly.b_service;

import com.boardly.b_service.jwt.JwtUtil;
import com.boardly.d_repository.UserRepository;
import com.boardly.e_model.User;
import com.boardly.f_dto.auth.AuthRequest;
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
        // TODO: Verifica se email ja esta cadastrado
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        // cria o User com dados request
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .avatarUrl(request.getAvatarUrl())
                .build();

        // salva usuario no MongoDB
        userRepository.save(user);

        // 4. Gera o token JWT
        String token = jwtUtil.generateToken(user); // esse método será criado

        // 5. Retorna o token na resposta
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        // TODO: implementar lógica de login
        return null;
    }
}
