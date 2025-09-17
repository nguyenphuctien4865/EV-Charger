package com.evcharger.architecture.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {
    private final String SECRET_KEY = "Tien"; // Use a strong secret key

//    private final Environment env;
//
//    public JwtUtils(Environment env) {
//        this.env = env;
//    }

    public String generateToken(Authentication authentication) {
        final String username = (String) authentication.getPrincipal();
        final Date expirationDate = new Date(System.currentTimeMillis() + Long.parseLong("86400000")); // 24 hours
        final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        final Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("email", authentication.getDetails());
        claims.put("roles", authentication.getAuthorities().stream()
                            .map(authority -> authority.getAuthority())
                            .toArray(String[]::new));   

        return Jwts.builder()
                    .claims(claims)
                    .subject(username)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(expirationDate)
                    .signWith(key).compact();
    }

    

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
