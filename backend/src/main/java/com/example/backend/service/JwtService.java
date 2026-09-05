package com.example.backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final String secret =
            "vK7#pL2!xQ9@rT4$zM8&nW3*eY6^cH1ZZ";

    private final SecretKey key =
            Keys.hmacShaKeyFor(
                    secret.getBytes(StandardCharsets.UTF_8)
            );

    private final long expiration = 1000L * 60 * 60 * 24;

    public String generateToken(String login) {

        Date now = new Date();

        return Jwts.builder()
                .subject(login)
                .issuedAt(now)
                .expiration(
                        new Date(now.getTime() + expiration)
                )
                .signWith(key)
                .compact();
    }

    public String extractLogin(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public boolean isTokenValid(String token) {

        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
