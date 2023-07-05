package com.example.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.model.MeetingRooms;
import com.example.model.ResultBean;
import com.example.service.MeetingroomMangeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class MeetingroomMangeController {
    @Resource
    MeetingroomMangeService meetingroomMangeService;


    @GetMapping("/api/meetingroomMange/getRoomAll")
    public JSONObject getRoomAll(int page, int pageSize){
        return meetingroomMangeService.getRoomAll(page,pageSize);
    }

    @GetMapping("/api/meetingroomMange/getRoomById")
    public JSONObject  getRoomById(int id){
        return meetingroomMangeService.getRoomById(id);
    }

    @GetMapping("/api/meetingroomMange/getRoomByName")
    public JSONObject  getRoomByName(String name){
        return meetingroomMangeService.getRoomByName(name);
    }

    @PostMapping("/api/meetingroomMange/addRoom")
    public Object addRoom(@RequestBody Map<String, Object> requestData) {
        double area = (double)requestData.get("area");
        System.out.println((String)requestData.get("buildName")+" "+(int)requestData.get("floor")+" "+(double)requestData.get("area")+" "+(int)requestData.get("capacity"));
        return meetingroomMangeService.addRoom((String)requestData.get("buildName"), (int)requestData.get("floor"),area,(int)requestData.get("capacity"));
    }
}
