
utility
package com.example.demo.util;

import io.jsonwebtoken.*;
import java.util.Date;

public class JwtUtil {

    private static final String KEY = "jwt-demo-key";

    public static String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        return extractClaims(token).getExpiration().after(new Date());
    }

    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public static String extractRole(String token) {
        return extractClaims(token).get("role").toString();
    }

    private static Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
Servlet

package com.example.demo.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/hello-servlet")
public class SimpleHelloServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setStatus(200);
        response.setContentType("text/plain");
        response.getWriter()
                .write("Hello from Simple Hello Servlet");
    }
}
Security config

package com.example.demo.config;

import org.springframework.context.annotation.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers(
                "/auth/**",
                "/hello-servlet",
                "/swagger-ui/**"
            ).permitAll()
            .anyRequest().authenticated();

        return http.build();
    }
}
