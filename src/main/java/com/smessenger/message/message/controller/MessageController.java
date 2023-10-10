package com.smessenger.message.message.controller;

import com.smessenger.message.message.entity.Message;
import com.smessenger.message.message.repository.MessageRepository;
import com.smessenger.message.shared.controller.MainController;
import lombok.RequiredArgsConstructor;
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
    public Mono<Message> insertMessage() {
        Message message = new Message();
        message.setUser_id(UUID.fromString("2decfef9-f289-4f5f-8764-d536efa7ded4"));
        message.setMessage("hello message");
        message.setCreated_at(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @DeleteMapping("/message")
    public Mono<String> deleteMessage() {
        return Mono.just("delete message");
    }

}
