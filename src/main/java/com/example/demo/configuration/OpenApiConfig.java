package com.example.demo.configuration;

import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Employee Service")
                        .description("Employee Details Service")
                        .version("1.0")
                        .contact(new Contact()
                        .name("Pavan Balasani")
                        .email("pavanbalasani@gmail.com")));
    }
}