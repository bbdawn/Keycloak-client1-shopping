package com.example.shopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class KeycloakController {

    private final WebClient KeycloakWebClient;

//    @GetMapping("/keycloak/login")
//    public String login(){
//        Mono<String> keycloakLogin =  WebClient.builder().baseUrl("http://localhost:8081/auth")
//                .build().get().uri("/realms/realm1/protocol/openid-connect/auth?response_type=code&client_id=client_shopping&redirect_uri=http://localhost:3001")
//                .retrieve()
//                .bodyToMono(String.class);
//        return keycloakLogin.block();
//    }
    @GetMapping("/keycloak/login")
    public String login(){
        Mono<String> keycloakLogin =  KeycloakWebClient
                                        .get()
                                        .uri("/realms/realm1/protocol/openid-connect/auth?response_type=code&client_id=client_shopping&redirect_uri=http://localhost:3001")
                                        .retrieve()
                                        .bodyToMono(String.class);

        return keycloakLogin.block();
    }

    @GetMapping("/keycloak/logout")
    public String logout(){
        Mono<String> keycloakLogout =  KeycloakWebClient
                                        .get()
                                        .uri("/realms/realm1/protocol/openid-connect/logout")
                                        .retrieve()
                                        .bodyToMono(String.class);

        return keycloakLogout.block();
    }


    @GetMapping("/new-token")
    public String newToken(){

        //TODO: 사용자의 정보 입력받아서 넣어야함
        //TODO: 인강에서 사용자 정보 없이도 토큰 받을 수 있는 방법 있었는지

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", "user_a");
        formData.add("password", "1");
        formData.add("client_id", "client_shopping");
        formData.add("client_secret", "Si0mMIO9e5toVMkPNN6YlqxubN3l3M1q");
        formData.add("grant_type", "password");

        String token =  KeycloakWebClient
                .post()
                .uri("/realms/realm1/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData(formData))
                .exchange()
                .flatMap(response -> response.bodyToMono(String.class))
                .log()
                .block();


        return token;
    }






}
