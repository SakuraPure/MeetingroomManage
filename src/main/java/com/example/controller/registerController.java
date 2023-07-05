package com.example.controller;

import com.example.service.AuthorizeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@CrossOrigin
public class registerController {
    @Resource
    AuthorizeService authorizeService;
    @PostMapping("/api/register")
    public Object register(@RequestBody Map<String, Object> requestData) {
        System.out.println((String)requestData.get("username")+" "+(String)requestData.get("password"));
        return authorizeService.register((String)requestData.get("username"), (String)requestData.get("password"));
    }
}
