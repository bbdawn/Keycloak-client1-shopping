package com.example.shopping.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {
    @Bean
    public WebClient KeycloakWebClient(){

        return WebClient.builder()
                .baseUrl("http://localhost:8081/auth")
                .build();

    }


}
