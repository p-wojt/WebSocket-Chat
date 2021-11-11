package com.example.websocketchat.service;

import com.example.websocketchat.entity.MessageEntity;
import com.example.websocketchat.repository.MessageRepository;
import com.example.websocketchat.service.impl.MessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;

class MessageServiceTests {

    private MessageServiceImpl service;
    private MessageRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = Mockito.mock(MessageRepository.class);
        this.service = new MessageServiceImpl(repository);
    }

    @Test
    public void saveMessage_allParamsOk_messageSavedCorrectly(){
        //given
        final MessageEntity messageEntity = new MessageEntity(1L, new Date(new java.util.Date().getTime()), "20:20", "192.168.1.1", "Alek", "Hello");
        //when
        this.service.save(messageEntity);
        //then
        Mockito.verify(repository).save(messageEntity);
    }

}
