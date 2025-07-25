package com.cl3880.finprodpref.controller;

import com.cl3880.finprodpref.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "${cors.allowed-origins}", allowCredentials = "true")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/accounts")
    public List<String> getUserAccounts(@PathVariable String userId) {
        return userService.getUserAccounts(userId);
    }
} 