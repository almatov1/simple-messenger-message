package com.smessenger.message.message.dao;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostMessageDao {
    @NotNull
    String message;
}
