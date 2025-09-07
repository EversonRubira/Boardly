package com.boardly.adapters.out.security;

import com.boardly.application.security.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;

@Component
@ConditionalOnProperty(name = "app.security.mode", havingValue = "jwt", matchIfMissing = true)
public class JwtTokenProvider implements TokenProvider {

    private final String secret;
    private final long expirationMs;
    private final String issuer;
    private final Key key;


    public JwtTokenProvider(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration-ms}") long expirationMs
    ) {
        this.secret = secret;
        this.expirationMs = expirationMs;
        this.issuer = "boardly"; // valor fixo ou opcional
        this.key = Keys.hmacShaKeyFor(io.jsonwebtoken.io.Decoders.BASE64.decode(secret));
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        long now = System.currentTimeMillis();

        return io.jsonwebtoken.Jwts.builder()
                .setSubject(userDetails.getUsername())   // email como subject
                .setIssuer(issuer)                       // garante quem emitiu
                .setIssuedAt(new java.util.Date(now))    // iat
                .setExpiration(new java.util.Date(now + expirationMs)) // exp
                .claim("roles", userDetails.getAuthorities()) // opcional, útil pra client
                .signWith(key, SignatureAlgorithm.HS256) // assinatura HMAC-SHA256
                .compact();
    }

    private Claims parseAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.key)    // mesma chave HMAC usada no sign
                .requireIssuer(this.issuer) // garante o mesmo issuer
                .build()
                .parseClaimsJws(token)      // valida assinatura e expiração
                .getBody();
    }

    @Override
    public String extractUsername(String token) {
        return parseAllClaims(token).getSubject();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        Claims claims = parseAllClaims(token);

        // 1) subject igual ao usuário esperado
        String username = claims.getSubject();
        if (username == null || !username.equals(userDetails.getUsername())) {
            return false;
        }

        // 2) não expirou
        java.util.Date expiration = claims.getExpiration();
        return expiration != null && expiration.after(new java.util.Date());
    }


}
