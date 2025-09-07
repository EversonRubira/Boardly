package com.boardly.application.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenProvider {

    /**
     * Gera um JWT para o usuário autenticado.
     * @param userDetails dados do usuário (username, authorities, etc).
     * @return token JWT assinado como String.
     */
    String generateToken(UserDetails userDetails);

    /**
     * Extrai o "username" (no nosso caso, o e-mail) do token.
     * @param token token JWT.
     * @return subject contido no JWT.
     */
    String extractUsername(String token);

    /**
     * Valida se o token é legítimo e corresponde ao usuário.
     * @param token token JWT.
     * @param userDetails usuário esperado.
     * @return true se válido; false se inválido.
     */
    boolean isTokenValid(String token, UserDetails userDetails);
}
