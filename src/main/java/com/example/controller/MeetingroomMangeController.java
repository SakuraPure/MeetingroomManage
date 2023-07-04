package com.example.controller;

import com.example.model.MeetingRooms;
import com.example.service.MeetingroomMangeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MeetingroomMangeController {
    @Resource
    MeetingroomMangeService meetingroomMangeService;

    @GetMapping("/meetingroomMange/getRoomAll")
    public List<MeetingRooms> getRoomAll(int page,int pageSize){
        return meetingroomMangeService.getRoomAll(page,pageSize);
    }
}
