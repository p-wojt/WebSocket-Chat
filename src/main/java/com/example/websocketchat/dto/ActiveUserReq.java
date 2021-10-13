package com.example.websocketchat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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


