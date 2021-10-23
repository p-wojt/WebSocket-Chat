package com.example.websocketchat.service;

import com.example.websocketchat.entity.MessageEntity;
import com.example.websocketchat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;

    public void save(final MessageEntity messageEntity){
        this.repository.save(messageEntity);
    }

    public void deleteLastDayMessages(){
        this.repository.deleteAll(this.repository.getLastDayMessages());
    }
}
