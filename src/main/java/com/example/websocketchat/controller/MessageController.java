package com.example.websocketchat.controller;

import com.example.websocketchat.entity.MessageEntity;
import com.example.websocketchat.model.Message;
import com.example.websocketchat.model.OutputMessage;
import com.example.websocketchat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message){
        String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        Date date = Date.valueOf(LocalDate.now());
        final MessageEntity msg = new MessageEntity(date, time, message.getIpAddress(), message.getFrom(), message.getText());
        this.service.save(msg);
        return new OutputMessage(message.getFrom(), message.getText(), message.getNickColor(), time);
    }
}
