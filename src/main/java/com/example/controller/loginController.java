package com.example.controller;

import com.example.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/api/getUserInfo")
    public Object getInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return authorizeService.getInfo(Integer.parseInt(token));
    }
}

