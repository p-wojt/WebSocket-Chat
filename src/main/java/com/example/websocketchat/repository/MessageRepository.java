package com.example.websocketchat.repository;

import com.example.websocketchat.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long>{

}
