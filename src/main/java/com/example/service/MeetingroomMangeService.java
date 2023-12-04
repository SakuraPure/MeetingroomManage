package com.example.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.DAO.ApprovalsDAO;
import com.example.DAO.ReserveDAO;
import com.example.DAO.ResultBeanDAO;
import com.example.DAO.RoomsDAO;
import com.example.model.MeetingApprovals;
import com.example.model.MeetingReservations;
import com.example.model.MeetingRooms;
import com.example.model.ResultBean;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeetingroomMangeService {

    @Resource
    RoomsDAO roomsDAO;

    @Resource
    ResultBean resultBean;

    @Resource
    ResultBeanDAO resultBeanDAO;

    @Resource
    ReserveDAO reserveDAO;

    @Resource
    ApprovalsDAO approvalsDAO;

    public JSONObject getRoomAll(int page,int pageSize){
//        System.out.println(page+" "+pageSize);
        if(page==1)
            page=0;
        else if (page>1) {
            int temp=page;
            page=(page-1)*pageSize;
            pageSize=temp*pageSize;
        }
        System.out.println(page+" "+pageSize);
        List<MeetingRooms> meetingRoomsList=roomsDAO.getRoomsAll(page,pageSize,null,null);
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
                return resultBeanDAO.roomSelectResult(meetingRoomsList,0,"未搜索到","success",0);
            }
            else
                return resultBeanDAO.roomSelectResult(meetingRoomsList,0,"success","success",count.size());
        }
        else if(queryMode.equals("buildName")){
            if(page==1)
                page=0;
            else if (page>1) {
                int temp=page;
                page=(page-1)*pageSize;
                pageSize=temp*pageSize;
            }
            List<MeetingRooms> meetingRoomsList=roomsDAO.getRoomsAll(page,pageSize,null,value);
            List<MeetingRooms> count=roomsDAO.getRoomsAll2();
            if(meetingRoomsList.size()==0){
                return resultBeanDAO.roomSelectResult(meetingRoomsList,0,"未搜索到","success",0);
            }
            else
                return resultBeanDAO.roomSelectResult(meetingRoomsList,0,"success","success",count.size());
        }
        return null;
    }

    public Object addRoom(Integer id,String buildName,int floor,double area,int capacity){
        MeetingRooms meetingRooms;
        JSONObject jsonObject=new JSONObject();
        int result=0;
        if(id!=null){
            if(roomsDAO.getRoomsAll(-1,0,id,null)!=null){
                jsonObject.put("code",1);
                jsonObject.put("message","id已存在");
                return jsonObject;
            }
            meetingRooms=new MeetingRooms().setBuildName(buildName)
                    .setFloor(floor)
                    .setArea(area)
                    .setCapacity(capacity)
                    .setId(id);
            result= roomsDAO.insertRoom(meetingRooms);
        } else if (id==null) {
            meetingRooms=new MeetingRooms().setBuildName(buildName)
                    .setFloor(floor)
                    .setArea(area)
                    .setCapacity(capacity);
            result = roomsDAO.insertRoomNoID(meetingRooms);
        }
        if(result!=0){
            jsonObject.put("code",0);
            jsonObject.put("message","success");

        }
        return jsonObject;
    }

    public JSONObject deleteRoom(int id){
        int result=roomsDAO.deleteRoom(id);
        JSONObject jsonObject=new JSONObject();
        if(result!=0){
            jsonObject.put("code",0);
            jsonObject.put("message","success");
        }
        else{
            jsonObject.put("code",1);
            jsonObject.put("message","删除失败");
        }
        return jsonObject;
    }

    public JSONObject updateRoom(int oid,Integer id,String buildName,int floor,double area,int capacity){
        if(id==null)id=oid;
        if(id!=oid&&roomsDAO.getRoomsAll(-1,0,id,null)!=null){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code",1);
            jsonObject.put("message","id已存在");
            return jsonObject;
        }
//        MeetingRooms meetingRooms=new MeetingRooms().setBuildName(buildName)
//                .setFloor(floor)
//                .setArea(area)
//                .setCapacity(capacity)
//                .setId(id);
        roomsDAO.updateRoom(oid,id,buildName,floor,capacity,area);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("message","success");
        return jsonObject;
    }

    public JSONObject reserveRoom(int tokenId, String topic, int roomId, LocalDateTime start,LocalDateTime end){
        MeetingReservations meetingReservations=new MeetingReservations().setUserId(tokenId)
                .setMeetingTopic(topic)
                .setRoomId(roomId)
                .setStartTime(start.withNano(0))
                .setEndTime(end.withNano(0));
        if(reserveDAO.countStart(meetingReservations.getStartTime())!=0){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code",1);
            jsonObject.put("message","预定失败，该时间段已被预定");
            return jsonObject;
        } else if (reserveDAO.countEnd(meetingReservations.getEndTime())!=0) {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code",1);
            jsonObject.put("message","预定失败，该时间段已被预定");
            return jsonObject;
        }
        int result=reserveDAO.insertReserve(meetingReservations);
        int reserveId=reserveDAO.getReserveByAll(meetingReservations).getId();
        System.out.println(reserveId);
        MeetingApprovals meetingApprovals=new MeetingApprovals().setReservationId(reserveId)
                .setApproverId(1)
                .setStatus("reviewing");
        int result2=approvalsDAO.insertApprovals(meetingApprovals);
        roomsDAO.updateRoomStatus(roomId,"reviewing");
        JSONObject resultJSON=new JSONObject();
        if(result!=0&&result2!=0){
            resultJSON.put("code",0);
            resultJSON.put("message","success");
        }
        else{
            resultJSON.put("code",1);
            resultJSON.put("message","fail");
        }
        return resultJSON;
    }
}
