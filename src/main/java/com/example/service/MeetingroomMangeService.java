package com.example.service;

import com.example.DAO.RoomsDAO;
import com.example.model.MeetingRooms;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MeetingroomMangeService {

    @Resource
    RoomsDAO roomsDAO;

    public List<MeetingRooms> getRoomAll(int page,int pageSize){
        List<MeetingRooms> meetingRoomsList=roomsDAO.getRoomsAll(page-1,pageSize);
        System.out.println(meetingRoomsList);
        return meetingRoomsList;
    }
}
