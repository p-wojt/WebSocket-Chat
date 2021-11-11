package com.example.websocketchat.service;

import com.example.websocketchat.entity.MessageEntity;
import com.example.websocketchat.repository.MessageRepository;

import java.util.List;

public interface MessageService {
    void save(MessageEntity messageEntity);
    List<MessageEntity> getLastDayMessages();
    void deleteLastDayMessages();
}
