package com.yurisaito.gestore.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.yurisaito.gestore.entity.UserAccess;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String jwtSecret;

    private String jwtIssuer = "auth-service";

    public String generateToken(UserAccess user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            String token = JWT.create()
                    .withIssuer(jwtIssuer)
                    .withSubject(user.getUsername())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token " + exception);
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.require(algorithm)
                    .withIssuer(jwtIssuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error decoding token: " + exception);
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            Instant expiryInstant = JWT.require(Algorithm.HMAC256(jwtSecret))
                    .withIssuer(jwtIssuer)
                    .build()
                    .verify(token)
                    .getExpiresAt()
                    .toInstant();

            // Compara a data de expiração com a data atual
            return expiryInstant != null && expiryInstant.isBefore(Instant.now());
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error verifying token: " + exception);
        }
    }

    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            JWT.require(algorithm)
                    .withIssuer(jwtIssuer)
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error validating token: " + exception);
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}