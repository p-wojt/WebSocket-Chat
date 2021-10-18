package com.example.websocketchat.controller;

import com.example.websocketchat.service.ActiveUserService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class ActiveUserControllerTests {

    private ActiveUserService service;

    @BeforeEach
    public void setup(){
        this.service = Mockito.mock(ActiveUserService.class);
    }

}
