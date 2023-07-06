package com.example.service;

import com.alibaba.fastjson2.JSONObject;
import com.example.DAO.ApprovalsDAO;
import com.example.DAO.ReserveDAO;
import com.example.DAO.ResultBeanDAO;
import com.example.DAO.RoomsDAO;
import com.example.model.MeetingApprovals;
import com.example.model.MeetingReservations;
import com.example.model.MeetingRooms;
import com.example.model.UserReserveInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MroomReserveService {

    @Resource
    ReserveDAO reserveDAO;

    @Resource
    ApprovalsDAO approvalsDAO;

    @Resource
    RoomsDAO roomsDAO;

    @Resource
    ResultBeanDAO resultBeanDAO;

    //本方法未完成，需要修改，未查询到的反馈信息没有写
    public JSONObject getUserAllReserve(int page,int pageSize,int userId){
        System.out.println("page:"+page+" pageSize:"+pageSize+" userId:"+userId);
        List<MeetingReservations> mRserve=reserveDAO.getReserveByUserId(userId,page-1,pageSize);
        System.out.println("mRserve:"+mRserve);
        List<MeetingReservations> count = reserveDAO.getReserveByUserId2(userId);
        System.out.println("count:"+count);
        MeetingRooms mRooms;
        MeetingApprovals mApproval;
        List<UserReserveInfo> reserveInfos=new ArrayList<>();

        for(MeetingReservations m:mRserve){
            mApproval=approvalsDAO.getApprovalsByReservationId(m.getId());
            mRooms=roomsDAO.getRoomsAll(-1,0,m.getRoomId(),null).get(0);
            UserReserveInfo userReserveInfo=new UserReserveInfo();
            userReserveInfo.setReserveId(m.getId());
            userReserveInfo.setRoomId(mRooms.getId());
            userReserveInfo.setBuildName(mRooms.getBuildName());
            userReserveInfo.setFloor(mRooms.getFloor());
            userReserveInfo.setStartTime(m.getStartTime());
            userReserveInfo.setEndTime(m.getEndTime());
            userReserveInfo.setMeetingTopic(m.getMeetingTopic());
            userReserveInfo.setStatus(mApproval.getStatus());
            System.out.println(userReserveInfo);
            reserveInfos.add(userReserveInfo);
        }
        return resultBeanDAO.roomSelectResult(reserveInfos,0,"success","success",count.size());
    }

    public JSONObject getMeeting(int page,int pageSize,int userId){
        List<MeetingReservations> mReserves = reserveDAO.getReserveByTime(page-1,pageSize,userId);
        List<MeetingReservations> count = reserveDAO.getReserveByTime(-1,0,userId);
        MeetingRooms mRooms;
        MeetingApprovals mApproval;
        List<UserReserveInfo> reserveInfos=new ArrayList<>();
        for(MeetingReservations m:mReserves){
            mApproval=approvalsDAO.getApprovalsByReservationId(m.getId());
            if(mApproval.getStatus().equals("approved")){
                mRooms=roomsDAO.getRoomsAll(-1,0,m.getRoomId(),null).get(0);
                UserReserveInfo userReserveInfo=new UserReserveInfo();
                userReserveInfo.setReserveId(m.getId())
                        .setRoomId(mRooms.getId())
                        .setBuildName(mRooms.getBuildName())
                        .setFloor(mRooms.getFloor())
                        .setStartTime(m.getStartTime())
                        .setEndTime(m.getEndTime())
                        .setMeetingTopic(m.getMeetingTopic())
                        .setStatus(mApproval.getStatus());
                reserveInfos.add(userReserveInfo);
            }
        }
        return resultBeanDAO.roomSelectResult(reserveInfos,0,"success","success",count.size());
    }
}
