package com.example.forum.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "your_secret_key";
    private static final long EXPIRATION_TIME = 86400000; // 1 día en milisegundos

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String validateToken(String token) throws JWTVerificationException {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);

        return decodedJWT.getSubject(); // Devuelve el nombre de usuario del token
    }
}
