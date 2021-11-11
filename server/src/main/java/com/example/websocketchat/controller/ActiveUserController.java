package com.example.websocketchat.controller;

import com.example.websocketchat.dto.ActiveUserReq;
import com.example.websocketchat.entity.ActiveUserEntity;
import com.example.websocketchat.service.impl.ActiveUserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/activeusers")
public class ActiveUserController {

    private final ActiveUserServiceImpl service;

    @PostMapping(path = "/save", consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody ActiveUserReq activeUser){
        this.service.saveActiveUser(activeUser);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<ActiveUserEntity>> getAllActiveUsers(){
        return ResponseEntity.ok(this.service.getAllActiveUsers());
    }

    @GetMapping(path = "/theSameNickname/{name}")
    public boolean isTheSameNickname(@PathVariable("name") String name){
        return service.isTheSameNickname(name);
    }

    @DeleteMapping(path = "/deleteByNickname/{name}")
    public void deleteUserByNickname(@PathVariable("name") String name){
        this.service.deleteUserByNickname(name);
    }
}
