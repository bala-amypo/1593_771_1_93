// package com.example.demo.util;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;

// @Component
// public class JwtUtil {

//     public String generateToken(String email, Long userId, String role) {
//         return "dummy-jwt-token";
//     }

//     public boolean validateToken(String token, UserDetails userDetails) {
//         return true;
//     }

//     public String extractUsername(String token) {
//         return null;
//     }

//     public Long extractUserId(String token) {
//         return null;
//     }

//     public String extractRole(String token) {
//         return null;
//     }
// }


package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ✅ Secret key (minimum 256 bits for HS256)
    private static final Key SECRET_KEY =
            Keys.hmacShaKeyFor("my-super-secret-key-for-jwt-signing-123456".getBytes());

    // ✅ Token validity (1 hour)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    // 🔐 Generate JWT Token
    public String generateToken(String email, Long userId, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔍 Extract all claims
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 🔍 Extract email
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 🔍 Extract role
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // 🔍 Extract userId
    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

    // ✅ Validate token
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
