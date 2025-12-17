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