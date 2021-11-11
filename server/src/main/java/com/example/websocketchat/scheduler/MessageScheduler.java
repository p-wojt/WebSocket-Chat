package com.example.websocketchat.scheduler;

import com.example.websocketchat.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
public class MessageScheduler {

    private final MessageServiceImpl service;
    private final long DELAY_TIME = 1000 * 60 * 60;

    @Autowired
    public MessageScheduler(final MessageServiceImpl service){
        this.service = service;
    }

    @Scheduled(fixedRate = DELAY_TIME)
    public void deleteOldMessages(){
        this.service.deleteLastDayMessages();
    }
}
