package com.example.shopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class KeycloakController {

    @GetMapping("/keycloak/login")
    public String login(){
        Mono<String> keycloakLogin =  WebClient.builder().baseUrl("http://localhost:8081/auth")
                .build().get().uri("/realms/realm1/protocol/openid-connect/auth?response_type=code&client_id=client_shopping")
                .retrieve()
                .bodyToMono(String.class);
        return keycloakLogin.block();
    }
}
