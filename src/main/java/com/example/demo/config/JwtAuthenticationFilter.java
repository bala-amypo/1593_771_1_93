package com.example.demo.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            if (JwtUtil.validateToken(token)) {

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                JwtUtil.extractUsername(token),
                                null,
                                null);

                SecurityContextHolder.getContext()
                        .setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}