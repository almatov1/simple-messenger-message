package com.smessenger.message.message.repository;

import com.smessenger.message.message.entity.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ReactiveCrudRepository<Message, String> {
}
