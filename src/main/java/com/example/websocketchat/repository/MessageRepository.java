package com.example.websocketchat.repository;

import com.example.websocketchat.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long>{
    @Query(value = "SELECT * FROM messages WHERE messages.date <= current_date - 1", nativeQuery = true)
    List<MessageEntity> getLastDayMessages();
}
