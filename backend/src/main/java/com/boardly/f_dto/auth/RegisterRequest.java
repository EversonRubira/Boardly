package com.boardly.f_dto.auth;

import com.boardly.e_model.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
    private String avatarUrl; // opcional
}