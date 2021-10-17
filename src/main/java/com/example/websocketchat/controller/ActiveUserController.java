package com.example.websocketchat.controller;

import com.example.websocketchat.dto.ActiveUserReq;
import com.example.websocketchat.entity.ActiveUserEntity;
import com.example.websocketchat.service.ActiveUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ActiveUserController {

    private final ActiveUserService service;

    @PostMapping(path = "/app/activeusers/save", consumes="application/json")
    public void saveUser(@RequestBody ActiveUserReq activeUser){
        this.service.saveActiveUser(activeUser);
    }

    @GetMapping(path = "/app/activeusers/getAll")
    public ResponseEntity<List<ActiveUserEntity>> getAllActiveUsers(){
        return ResponseEntity.ok(this.service.getAllActiveUsers());
    }

    @DeleteMapping(path = "/app/activeusers/deleteByNickname/{name}")
    public void deleteUserByNickname(@PathVariable("name") String name){
        this.service.deleteUserByNickname(name);
    }
}
