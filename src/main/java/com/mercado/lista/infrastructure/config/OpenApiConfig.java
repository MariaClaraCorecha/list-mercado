package com.mercado.lista.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI listaMercadoOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Lista de Mercado API")
                .description("To-do de compras de mercado: cadastre itens, filtre por status/categoria e marque como comprado.")
                .version("v1"));
    }
}
