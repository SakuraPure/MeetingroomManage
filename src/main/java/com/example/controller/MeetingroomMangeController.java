package com.example.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.model.MeetingRooms;
import com.example.model.ResultBean;
import com.example.service.MeetingroomMangeService;
import org.springframework.data.relational.core.sql.In;
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

    @GetMapping("/api/meetingroomMange/getRoom")
    public <T> JSONObject getRoom(int page,int pageSize,String queryMode,@RequestParam("value")String value){
            return meetingroomMangeService.getRoom(page,pageSize,queryMode,value);
    }

    @PostMapping("/api/meetingroomMange/addRoom")
    public Object addRoom(@RequestBody Map<String, Object> requestData) {
        double area = 0;
        if(requestData.get("area") instanceof Integer){
            area = ((Integer) requestData.get("area")).doubleValue();
        } else if (requestData.get("area") instanceof Double) {
            area = (double)requestData.get("area")*1.0;
        }

        Integer id = (Integer)requestData.get("id");
        //System.out.println((String)requestData.get("buildName")+" "+(int)requestData.get("floor")+" "+(double)requestData.get("area")+" "+(int)requestData.get("capacity"));
        return meetingroomMangeService.addRoom(id,(String)requestData.get("buildName"),(int)requestData.get("floor"),area,(int)requestData.get("capacity"));
    }
}
