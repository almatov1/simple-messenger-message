package com.smessenger.message.message.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Message {
    @Id
    private UUID id;
    private UUID user_id;
    private String message;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
