package com.example.websocketchat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "Messages")
@Getter
@NoArgsConstructor
public class MessageEntity {
    @GeneratedValue
    @Id
    private Long id;

    private Date date;

    private String time;

    private String ipAddress;

    private String nickname;

    private String text;


    public MessageEntity(Date date, String time, String ipAddress, String nickname, String text) {
        this.date = date;
        this.time = time;
        this.ipAddress = ipAddress;
        this.nickname = nickname;
        this.text = text;
    }
}
