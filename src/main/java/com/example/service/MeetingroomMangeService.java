package com.example.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.DAO.ResultBeanDAO;
import com.example.DAO.RoomsDAO;
import com.example.model.MeetingRooms;
import com.example.model.ResultBean;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MeetingroomMangeService {

    @Resource
    RoomsDAO roomsDAO;

    @Resource
    ResultBean resultBean;

    @Resource
    ResultBeanDAO resultBeanDAO;

    public JSONObject getRoomAll(int page,int pageSize){
//        System.out.println(page+" "+pageSize);

        List<MeetingRooms> meetingRoomsList=roomsDAO.getRoomsAll(page-1,pageSize,null,null);
        List<MeetingRooms> count=roomsDAO.getRoomsAll2();
//        System.out.println(meetingRoomsList);
        return resultBeanDAO.roomSelectResult(meetingRoomsList,0,"success","success",count.size());
    }

    public JSONObject getRoom(int page,int pageSize,String queryMode,String value){
        if(queryMode.equals("id")){
            System.out.println(value+" "+value.getClass());
            Integer id=Integer.parseInt(value);
            System.out.println(value+" "+value.getClass());
            List<MeetingRooms> meetingRoomsList=roomsDAO.getRoomsAll(-1,0,id,null);
            List<MeetingRooms> count=roomsDAO.getRoomsAll2();
            if(meetingRoomsList.size()==0){
                return resultBeanDAO.roomSelectResult(meetingRoomsList,1,"未搜索到","success",0);
            }
            else
                return resultBeanDAO.roomSelectResult(meetingRoomsList,0,"success","success",count.size());
        }
        else if(queryMode.equals("buildName")){
            List<MeetingRooms> meetingRoomsList=roomsDAO.getRoomsAll(page,pageSize,null,value);
            List<MeetingRooms> count=roomsDAO.getRoomsAll2();
            if(meetingRoomsList.size()==0){
                return resultBeanDAO.roomSelectResult(meetingRoomsList,1,"未搜索到","success",0);
            }
            else
                return resultBeanDAO.roomSelectResult(meetingRoomsList,0,"success","success",count.size());
        }
        return null;
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

    public Object updateRoom(int id,String buildName,int floor,double area,int capacity){
        MeetingRooms meetingRooms=new MeetingRooms().setBuildName(buildName)
                .setFloor(floor)
                .setArea(area)
                .setCapacity(capacity);
        roomsDAO.updateRoom(id,meetingRooms);
        return null;
    }


}
