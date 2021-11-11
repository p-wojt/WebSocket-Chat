package com.example.websocketchat.service.impl;

import com.example.websocketchat.exception.NoUserFoundException;
import com.example.websocketchat.dto.ActiveUserReq;
import com.example.websocketchat.entity.ActiveUserEntity;
import com.example.websocketchat.repository.ActiveUserRepository;
import com.example.websocketchat.service.ActiveUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActiveUserServiceImpl implements ActiveUserService {

    private final ActiveUserRepository repository;

    @Override
    public void saveActiveUser(ActiveUserReq activeUser){
        final ActiveUserEntity activeUserEntity = new ActiveUserEntity(activeUser.getNickname(), activeUser.getColor());
        this.repository.save(activeUserEntity);
    }

    @Override
    public List<ActiveUserEntity> getAllActiveUsers(){
        return this.repository.findAll();
    }

    @Transactional
    @Modifying
    @Override
    public void deleteUserByNickname(String nickname) {
        final Optional<ActiveUserEntity> optActiveUser = this.repository.findByNickname(nickname);
        final ActiveUserEntity activeUser = optActiveUser.orElseThrow(
                () -> {
                    throw new NoUserFoundException("User with this nickname doesn't exist!");
                }
        );
        this.repository.delete(activeUser);
    }

    @Override
    public boolean isTheSameNickname(String name) {
        return repository.findByNickname(name).isPresent();
    }
}
