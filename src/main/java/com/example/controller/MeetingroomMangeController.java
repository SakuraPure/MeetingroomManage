package com.example.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.model.MeetingRooms;
import com.example.model.ResultBean;
import com.example.service.MeetingroomMangeService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class MeetingroomMangeController {
    @Resource
    MeetingroomMangeService meetingroomMangeService;


//    @GetMapping("/api/meetingroomMange/getRoomAll")
//    public JSONObject getRoomAll(int page, int pageSize){
//        return meetingroomMangeService.getRoomAll(page,pageSize);
//    }

    @GetMapping("/api/meetingroomMange/getRoomAll")
    public JSONObject getRoomAll(int page,int pageSize,String queryMode,String value){
        if(queryMode!=null&&value!=null){
            return meetingroomMangeService.getRoom(page,pageSize,queryMode,value);
        }else if(queryMode==null&&value==null) {
            return meetingroomMangeService.getRoomAll(page,pageSize);
        } else if (queryMode!=null&&value==null) {
            return meetingroomMangeService.getRoomAll(page,pageSize);
        }

        return null;
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

    @PostMapping("/api/meetingroomMange/reserveRoom")
    public JSONObject reserveRoom(HttpServletRequest request,@RequestBody Map<String, Object> requestData) {
        int token = Integer.parseInt(request.getHeader("Authorization"));
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse((String)requestData.get("startTime"), formatter);
        LocalDateTime end = LocalDateTime.parse((String)requestData.get("endTime"), formatter);
        return meetingroomMangeService.reserveRoom(token, (String)requestData.get("meetingTopic"), (int)requestData.get("roomId"), start, end);
    }

    @PostMapping("/api/meetingroomMange/updateRoom")
    public JSONObject updateRoom(@RequestBody Map<String, Object> requestData) {
        System.out.println(requestData);
        double area = 0;
        if(requestData.get("area") instanceof Integer){
            area = ((Integer) requestData.get("area")).doubleValue();
        } else if (requestData.get("area") instanceof Double) {
            area = (double)requestData.get("area")*1.0;
        }
        Integer id = (Integer)requestData.get("id");
        return meetingroomMangeService.updateRoom((Integer)requestData.get("oldId"),id,(String)requestData.get("buildName") ,(int)requestData.get("floor"),area,(int)requestData.get("capacity"));
    }

    @PostMapping("/api/meetingroomMange/deleteRoom")
    public JSONObject deleteRoom(@RequestBody Map<String, Object> requestData) {
        return meetingroomMangeService.deleteRoom((Integer)requestData.get("id"));
    }
}
