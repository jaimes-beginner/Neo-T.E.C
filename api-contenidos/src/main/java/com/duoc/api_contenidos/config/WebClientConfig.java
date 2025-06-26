package com.duoc.api_contenidos.config;

/*------------------------------------------*/

// Importaciones
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/*------------------------------------------*/

@Configuration
public class WebClientConfig {
    
    // Puerto hacia cursos: 8083
    @Bean
    public WebClient cursosWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8083") .build();
    }

}
