package com.boardly.e_model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, MEMBER;

    @Override
    public String getAuthority() {
        return name(); // retorna "ADMIN" ou "MEMBER"
    }
}