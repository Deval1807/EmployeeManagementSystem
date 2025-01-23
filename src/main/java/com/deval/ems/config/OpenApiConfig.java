package com.deval.ems.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // Parse openapi.yml file for custom swagger configuration
    @Bean
    public OpenAPI customApiConfig() {
        return new OpenAPIV3Parser().read("src/main/resources/openapi.yml");
    }
}
