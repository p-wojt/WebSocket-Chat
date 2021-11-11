package com.example.websocketchat.service;

import com.example.websocketchat.dto.ActiveUserReq;
import com.example.websocketchat.entity.ActiveUserEntity;

import java.util.List;

public interface ActiveUserService {
    void saveActiveUser(ActiveUserReq activeUser);
    List<ActiveUserEntity> getAllActiveUsers();
    void deleteUserByNickname(String nickname);
    boolean isTheSameNickname(String name);
}
