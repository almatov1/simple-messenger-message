package com.smessenger.message.message.controller;

import com.smessenger.message.message.dao.PostMessageDao;
import com.smessenger.message.message.entity.Message;
import com.smessenger.message.message.service.MessageService;
import com.smessenger.message.shared.controller.MainController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MessageController extends MainController {
    private final MessageService messageService;

    @PostMapping("/message")
    public Mono<Message> insertMessage(@AuthenticationPrincipal Jwt principal, @Valid @RequestBody PostMessageDao postMessageDao) {
        return messageService.insertMessage(principal, postMessageDao);
    }

    @RabbitListener(queues = "messages")
    public void deleteMessage(String message) {
        messageService.deleteMessage(message).subscribe();
    }
}
