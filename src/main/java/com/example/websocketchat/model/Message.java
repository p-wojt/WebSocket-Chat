package com.example.websocketchat.model;

import lombok.Getter;

@Getter
public class Message {
    private final String from;
    private final String text;
    private final String nickColor;
    private final String ipAddress;

    public Message(String from, String text, String nickColor, String ipAddress) {
        this.from = from;
        this.text = text;
        this.nickColor = nickColor;
        this.ipAddress = ipAddress;
    }
}
