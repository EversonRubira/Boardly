package com.boardly.domain.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String id;        // só um identificador lógico
    private String name;
    private String email;
    private String password;  // deixa aqui para usar depois no JWT (pode ficar null por enquanto)
    private Role role;
    private String avatarUrl;
}
