package com.example.digico_labs.controller;

import com.example.digico_labs.repository.model.LoginRequest;
import com.example.digico_labs.repository.model.RegisterRequest;
import com.example.digico_labs.repository.model.user.AuthenticationUser;
import com.example.digico_labs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> loggingUser(@RequestBody LoginRequest loginRequest) {

        Map<String,Object> loggedUser = userService.logUser(loginRequest);

        return ResponseEntity.ok(loggedUser);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> registerUser(@RequestBody RegisterRequest registerRequest) {
        Map<String, Object> savedUser = userService.saveUser(registerRequest);

        return ResponseEntity.ok(savedUser);
    }

}
