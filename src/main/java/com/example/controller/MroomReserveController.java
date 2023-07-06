package com.example.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.service.MroomReserveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MroomReserveController {

    @Resource
    MroomReserveService mroomReserveService;

    @GetMapping("/api/mroomReserve/getRoomAll")
    public JSONObject getRserveInfo(int page,int pageSize,int userId){
        return mroomReserveService.getUserAllReserve(page,pageSize,userId);
    }
}
