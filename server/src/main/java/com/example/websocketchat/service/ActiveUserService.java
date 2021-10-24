package com.example.websocketchat.service;

import com.example.websocketchat.dto.ActiveUserReq;
import com.example.websocketchat.entity.ActiveUserEntity;
import com.example.websocketchat.repository.ActiveUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActiveUserService {

    private final ActiveUserRepository repository;

    public void saveActiveUser(ActiveUserReq activeUser){
        this.repository.save(new ActiveUserEntity(activeUser.getNickname(), activeUser.getColor()));
    }

    public List<ActiveUserEntity> getAllActiveUsers(){
        return this.repository.findAll();
    }

    public void deleteUserByNickname(String nickname) {
        this.repository.deleteUserByNickname(nickname);
    }

    public boolean isTheSameNickname(String name) {
        return repository.findByNickname(name) != null;
    }
}
