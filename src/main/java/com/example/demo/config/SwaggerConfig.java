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
        final String securitySchemeName = "bearerAuth";
        
        return new OpenAPI()
                .info(new Info()
                        .title("Drug Interaction API")
                        .version("1.0")
                        .description("API for managing medications and checking interactions."))
                // 1. Keep your custom server URL
                .servers(List.of(
                        new Server().url("https://9117.pro604cr.amypo.ai/")
                ))
                // 2. This adds the Lock Icon / Authorize button to Swagger
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // 3. This tells Swagger that we use JWT Bearer Tokens
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}