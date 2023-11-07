package com.smessenger.message.message.dao;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteMessageResponseDao {
    @NotNull
    private String messageId;
}
