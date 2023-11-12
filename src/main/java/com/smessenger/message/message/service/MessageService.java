package com.smessenger.message.message.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smessenger.message.message.dao.DeleteMessageResponseDao;
import com.smessenger.message.message.dao.PostMessageDao;
import com.smessenger.message.message.entity.Message;
import com.smessenger.message.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
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

    public Mono<Void> deleteMessage(String message) {
        log.info("message: ${}", message);
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("objectMapper created");
        DeleteMessageResponseDao deleteMessageResponseDao;
        try {
            deleteMessageResponseDao = objectMapper.readValue(message, DeleteMessageResponseDao.class);
        } catch (JsonProcessingException e) {
            log.error("json problem");
            return Mono.error(e);
        }

        log.info("message id: ${}", deleteMessageResponseDao.getMessageId());
        String messageId = deleteMessageResponseDao.getMessageId();
        return messageRepository.deleteById(messageId);
    }
}
