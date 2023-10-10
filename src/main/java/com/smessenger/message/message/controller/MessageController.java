package com.smessenger.message.message.controller;

import com.smessenger.message.message.entity.Message;
import com.smessenger.message.message.repository.MessageRepository;
import com.smessenger.message.shared.controller.MainController;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MessageController extends MainController {
    private final MessageRepository messageRepository;

    @PostMapping("/message")
    public Mono<Message> insertMessage(@AuthenticationPrincipal Jwt principal) {
        Message message = new Message();
        message.setUser_id(UUID.fromString(principal.getSubject()));
        message.setMessage("hello message");
        message.setCreated_at(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @DeleteMapping("/message")
    public Mono<String> deleteMessage(Authentication authentication) {
        return Mono.just("Hello admin, " + authentication.getName());
    }
}
