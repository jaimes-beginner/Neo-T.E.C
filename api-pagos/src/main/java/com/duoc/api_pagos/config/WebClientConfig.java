package com.duoc.api_pagos.config;

/*------------------------------------------*/

// Importaciones
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/*------------------------------------------*/

@Configuration
public class WebClientConfig {
    
    @Bean
    public WebClient usuarioWebClient() {
        return WebClient.builder()
            .baseUrl("http://localhost:8080") // URL de usuarios
            .build();
    }

    @Bean
    public WebClient cursoWebClient() {
        return WebClient.builder()
            .baseUrl("http://localhost:8081") // URL de cursos
            .build();
    }
}

