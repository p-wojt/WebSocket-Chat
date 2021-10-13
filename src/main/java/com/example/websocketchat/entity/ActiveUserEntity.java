package com.example.websocketchat.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ActiveUsers")
@RequiredArgsConstructor
@Getter
public class ActiveUserEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String nickname;

    private String color;


    public ActiveUserEntity(String nickname, String color) {
        this.nickname = nickname;
        this.color = color;
    }
}
