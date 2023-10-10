package com.smessenger.message.message.service;

import com.smessenger.message.message.dao.PostMessageDao;
import com.smessenger.message.message.entity.Message;
import com.smessenger.message.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Mono<Message> insertMessage(@AuthenticationPrincipal Jwt principal, PostMessageDao postMessageDao) {
        Message message = new Message();
        message.setUser_id(UUID.fromString(principal.getSubject()));
        message.setMessage(postMessageDao.getMessage());
        message.setCreated_at(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public Mono<String> deleteMessage(Authentication authentication) {
        return Mono.just("Hello admin, " + authentication.getName());
    }
}
