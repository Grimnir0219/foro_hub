package com.example.forum.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API del Foro")
                        .version("1.0")
                        .description("Documentación de la API del Foro. Aquí puedes encontrar información sobre los endpoints disponibles."));
    }
}

