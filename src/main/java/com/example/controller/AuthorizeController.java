package com.example.controller;

import com.example.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin
public class AuthorizeController {
    @Autowired
    AuthorizeService authorizeService;

    @PostMapping("/login")
    public Object login(@RequestParam(required = false) String username, String password) {
        return authorizeService.login(username, password);
    }

    @PostMapping("/register")
    public Object register(@RequestParam(required=false) String username, String password) {
        return authorizeService.register(username, password);
    }
}

