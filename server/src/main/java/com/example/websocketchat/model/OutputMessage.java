package com.example.websocketchat.model;

import lombok.Getter;

@Getter
public class OutputMessage {
    private final String from;
    private final String text;
    private final String nickColor;
    private final String time;

    public OutputMessage(String from, String text, String nickColor, String time) {
        this.from = from;
        this.text = text;
        this.nickColor = nickColor;
        this.time = time;
    }
}
