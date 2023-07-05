package com.example.controller;

import com.example.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin
public class loginController {
    @Autowired
    AuthorizeService authorizeService;

    @PostMapping("/api/login")
    public Object login(@RequestBody Map<String, Object> requestData) {
        System.out.println((String)requestData.get("username")+" "+(String)requestData.get("password"));
        return authorizeService.login((String)requestData.get("username"), (String)requestData.get("password"));
    }

    @PostMapping("/api/getInfo")
    public Object getInfo(@RequestBody Map<String, Object> requestData) {
        return authorizeService.getInfo((int)requestData.get("token"));
    }
}

