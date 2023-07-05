package com.example.DAO;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

@Mapper
public interface ReserveDAO {
    @Results({
            @Result(column = "f_id",property = "id"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "room_id",property = "roomId"),
            @Result(column = "start_time",property = "startTime"),
            @Result(column = "end_time",property = "endTime"),
            @Result(column = "meeting_topic",property = "meetingTopic"),
    })
    @Insert("insert into meeting_reservations(user_id,room_id,start_time,end_time,meeting_topic) values(#{userId},#{roomId},#{startTime},#{endTime},#{meetingTopic})")
    int insertReserve(int userId,int roomId,String startTime,String endTime,String meetingTopic);
}
