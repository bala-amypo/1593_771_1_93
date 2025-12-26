package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("20_Drug Interaction Checker API")
                        .version("1.0")
                        .description("API Documentation for Drug Interaction Checker"))
                // Setting the specific server URL as requested
                .servers(List.of(
                        new Server().url("https://9117.pro604cr.amypo.ai")
                ))
                // Adds the global security requirement so all endpoints show the lock icon
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                // Defines the JWT security scheme
                .components(new Components().addSecuritySchemes("Bearer Authentication", createSecurityScheme()));
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name("Bearer Authentication")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }
}