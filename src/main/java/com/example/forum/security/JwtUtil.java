package com.example.forum.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;

    public JwtUtil() {
        // Genera una clave segura para HS256
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    /**
     * Genera un token JWT para el usuario dado.
     *
     * @param username El nombre del usuario para el que se genera el token.
     * @return El token JWT generado.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(key)
                .compact();
    }

    /**
     * Extrae el nombre del usuario del token JWT.
     *
     * @param token El token JWT.
     * @return El nombre del usuario contenido en el token.
     */
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Verifica si el token JWT es válido para el usuario dado.
     *
     * @param token El token JWT.
     * @param username El nombre del usuario.
     * @return True si el token es válido, False en caso contrario.
     */
    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    /**
     * Verifica si el token JWT ha expirado.
     *
     * @param token El token JWT.
     * @return True si el token ha expirado, False en caso contrario.
     */
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    /**
     * Obtiene los claims del token JWT.
     *
     * @param token El token JWT.
     * @return Los claims contenidos en el token.
     */
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
