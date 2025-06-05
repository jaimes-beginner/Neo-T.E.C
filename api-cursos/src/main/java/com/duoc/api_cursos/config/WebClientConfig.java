package com.duoc.api_cursos.config;

/*------------------------------------------*/

// Importaciones
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/*------------------------------------------*/

@Configuration
public class WebClientConfig {
    
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

}
