package com.example.websocketchat.service;

import com.example.websocketchat.entity.ActiveUserEntity;
import com.example.websocketchat.repository.ActiveUserRepository;
import com.example.websocketchat.service.impl.ActiveUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActiveUserServiceTests {

    private ActiveUserServiceImpl service;
    private ActiveUserRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = Mockito.mock(ActiveUserRepository.class);
        this.service = new ActiveUserServiceImpl(repository);
    }

    @Test
    public void gettingAllActiveUsers_allParamsOk_returnedAllSavedActiveUsers(){
        //given
        final ActiveUserEntity activeUserEntity1 = new ActiveUserEntity(1L,"Patryk", "Blue");
        final ActiveUserEntity activeUserEntity2 = new ActiveUserEntity(2L,"Bartek", "Yellow");
        List<ActiveUserEntity> expectedList = List.of(activeUserEntity1, activeUserEntity2);
        Mockito.when(repository.findAll()).thenReturn(expectedList);
        //when
        List<ActiveUserEntity> receivedList = this.service.getAllActiveUsers();
        //then
        assertEquals(expectedList, receivedList);
    }

    @Test
    public void deleteUserByNickname_allParamsOk_userDeletedCorrectly(){
        //given
        final ActiveUserEntity activeUserEntity = new ActiveUserEntity(1L,"Patryk", "Blue");
        Mockito.when(this.repository.findByNickname("Patryk")).thenReturn(Optional.of(activeUserEntity));
        //when
        this.service.deleteUserByNickname("Patryk");
        //then
        Mockito.verify(repository).delete(activeUserEntity);
    }
}
