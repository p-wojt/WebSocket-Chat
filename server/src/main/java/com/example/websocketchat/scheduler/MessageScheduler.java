package com.example.websocketchat.scheduler;

import com.example.websocketchat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
public class MessageScheduler {

    private final MessageService service;
    private final long DELAY_TIME = 1000 * 60 * 60;

    @Autowired
    public MessageScheduler(final MessageService service){
        this.service = service;
    }

    @Scheduled(fixedRate = DELAY_TIME)
    public void deleteOldMessages(){
        this.service.deleteLastDayMessages();
    }
}
