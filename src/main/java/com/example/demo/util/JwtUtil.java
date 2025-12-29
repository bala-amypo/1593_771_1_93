
package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // A secure secret key (at least 256 bits for HS256)
    private final String SECRET_KEY_STR = "your-very-secure-and-long-secret-key-for-drug-interaction-checker";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STR.getBytes(StandardCharsets.UTF_8));

    // Token expiration time (e.g., 10 hours)
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    /**
     * Creates a JWT with user ID, email, role, issued-at, and expiration.
     * Required by Rule 8.1 and Test 32.
     */
    public String generateToken(String email, Long userId, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates token signature, expiration, and subject.
     * Required by Rule 8.1 and Test 33.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Returns the email (Subject).
     * Required by Rule 8.1 and Test 34.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Returns user ID claim.
     * Required by Rule 8.1 and Test 34.
     */
    public Long extractUserId(String token) {
        final Claims claims = extractAllClaims(token);
        Object userId = claims.get("userId");
        if (userId instanceof Integer) {
            return ((Integer) userId).longValue();
        }
        return (Long) userId;
    }

    /**
     * Returns role claim.
     * Required by Rule 8.1 and Test 34.
     */
    public String extractRole(String token) {
        final Claims claims = extractAllClaims(token);
        return (String) claims.get("role");
    }

    // --- Helper Methods ---

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}