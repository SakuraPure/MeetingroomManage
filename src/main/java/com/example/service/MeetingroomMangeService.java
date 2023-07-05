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
        System.out.println(page+" "+pageSize);
        List<MeetingRooms> meetingRoomsList=roomsDAO.getRoomsAll(page-1,pageSize,null,null);
        System.out.println(meetingRoomsList);
        return meetingRoomsList;
    }

    public List<MeetingRooms> getRoomById(int id){
        List<MeetingRooms> meetingRoomsList=roomsDAO.getRoomsAll(-1,0,id,null);
        System.out.println(meetingRoomsList);
        return meetingRoomsList;
    }

    public List<MeetingRooms> getRoomByName(String name){
        List<MeetingRooms> meetingRoomsList=roomsDAO.getRoomsAll(-1,0,null,name);
        System.out.println(meetingRoomsList);
        return meetingRoomsList;
    }

    public Object addRoom(String buildName,int floor,double area,int capacity){
        MeetingRooms meetingRooms=new MeetingRooms().setBuildName(buildName)
                                                    .setFloor(floor)
                                                    .setArea(area)
                                                    .setCapacity(capacity);
        int result=roomsDAO.insertRoom(meetingRooms);
        if(result==1) {
            return true;
        }
        else {
            return false;
        }
    }

    public Object deleteRoom(int id){
        int result=roomsDAO.deleteRoom(id);
        return null;
    }


}
