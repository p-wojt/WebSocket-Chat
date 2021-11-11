package com.example.websocketchat.service.impl;

import com.example.websocketchat.entity.MessageEntity;
import com.example.websocketchat.repository.MessageRepository;
import com.example.websocketchat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;

    @Override
    public void save(MessageEntity messageEntity){
        this.repository.save(messageEntity);
    }

    @Override
    public List<MessageEntity> getLastDayMessages(){
        return this.repository.getLastDayMessages();
    }

    @Override
    public void deleteLastDayMessages(){
        final List<MessageEntity> messages = this.getLastDayMessages();
        this.repository.deleteAll(messages);
    }
}