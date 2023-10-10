package com.smessenger.message.shared.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/")
public class MainController {

    @GetMapping("health")
    public Mono<String> health() {
        return Mono.just("OK");
    }
}
