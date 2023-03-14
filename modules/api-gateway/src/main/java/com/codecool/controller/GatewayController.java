package com.codecool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.security.Principal;

@RestController
public class GatewayController {

    @GetMapping("/login")
    public Mono<Void> login(ServerHttpResponse response, Principal principal) {
        response.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
        response.getHeaders().setLocation(URI.create("http://127.0.0.1:3000/success/" + principal.getName()));
        return response.setComplete();
    }
}
