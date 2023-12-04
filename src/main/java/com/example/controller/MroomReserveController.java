package com.example.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.service.MroomReserveService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin
public class MroomReserveController {

    @Resource
    MroomReserveService mroomReserveService;

    @GetMapping("/api/mroomReserve/getRoomAll")
    public JSONObject getRserveInfo(int page, int pageSize, HttpServletRequest request){
        return mroomReserveService.getUserAllReserve(page,pageSize,request.getIntHeader("Authorization"));
    }

    @PostMapping("/api/mroomReserve/cancelReserve")
    public JSONObject cancelReserve(@RequestBody Map<String, Object> requestData) {
        return mroomReserveService.rollbackReserve((int) requestData.get("reserveId"));
    }
}
