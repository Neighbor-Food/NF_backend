package com.uracle.nfbackend.nfbackend.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uracle.nfbackend.nfbackend.entity.User;
import com.uracle.nfbackend.nfbackend.service.UserService;

import lombok.RequiredArgsConstructor;

//Todo : 테스트 완료 후 Member로 변경해야함

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam/svc/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Object>  getUsers() throws ExecutionException, InterruptedException {
        List<User> list = userService.getUsers();
        return ResponseEntity.ok().body(list);

    }


}