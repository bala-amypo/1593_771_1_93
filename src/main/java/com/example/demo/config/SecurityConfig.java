package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF for APIs
            .csrf(csrf -> csrf.disable())

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                // Allow Swagger UI & API docs
                .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui/index.html",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ).permitAll()

                // Allow auth endpoints
                .requestMatchers("/auth/**").permitAll()

                // Allow everything else (for learning/demo)
                .anyRequest().permitAll()
            )

            // No login form, no basic auth
            .httpBasic(httpBasic -> httpBasic.disable())
            .formLogin(form -> form.disable());

        return http.build();
    }
}
