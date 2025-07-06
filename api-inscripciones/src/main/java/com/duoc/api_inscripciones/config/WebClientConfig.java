package com.duoc.api_inscripciones.config;

/*------------------------------------------*/

// Importaciones
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/*------------------------------------------*/

@Configuration
public class WebClientConfig {

    // PAGO WEB CLIENT: Microservicio de pago
    @Bean
    public WebClient pagoWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8084") 
                .build();
    }

    // USUARIO WEB CLIENT: Microservicio de usuarios
    @Bean
    public WebClient usuarioWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080") 
                .build();
    }

    // CURSO WEB CLIENT: Microservicio de cursos
    @Bean
    public WebClient cursoWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8081")  
                .build();
    }
}