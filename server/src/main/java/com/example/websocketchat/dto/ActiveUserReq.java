package com.example.websocketchat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ActiveUserReq {
    private String nickname;
    private String color;

    public ActiveUserReq(String nickname, String color) {
        this.nickname = nickname;
        this.color = color;
    }
}


