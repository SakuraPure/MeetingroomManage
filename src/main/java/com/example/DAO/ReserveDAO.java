package com.example.DAO;


import com.example.model.MeetingReservations;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    int insertReserve(MeetingReservations meetingReservations);

    @Results({
            @Result(column = "f_id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "room_id", property = "roomId"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "meeting_topic", property = "meetingTopic"),
    })
    @Select("<script>select * from meeting_reservations where user_id=#{userId}"+
            "<if test=\"page != -1 and pageSize !=0\">"+
            "limit #{page},#{pageSize}"+
            "</if>"+
            "</script>")
    List<MeetingReservations> getReserveByUserId(@Param("userId")int userId,@Param("page")int page,@Param("pageSize")int pageSize);

    @Results({
            @Result(column = "f_id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "room_id", property = "roomId"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "meeting_topic", property = "meetingTopic"),
    })
    @Select("select * from meeting_reservations where user_id=#{userId}")
    List<MeetingReservations> getReserveByUserId2(@Param("userId") int userId);//获取所有预约信息

    @Results({
            @Result(column = "f_id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "room_id", property = "roomId"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "meeting_topic", property = "meetingTopic"),
    })
    @Select("<script>SELECT * FROM meeting_reservations"+
            "WHERE user_id = #{userId} AND start_time &lt; NOW() AND end_time &gt; NOW();"+
            "<if test=\"page != -1 and pageSize !=0\">"+
            "limit #{page},#{pageSize}"+
            "</if>"+
            "</script>")
    List<MeetingReservations> getReserveByTime(@Param("page")int page,@Param("pageSize")int pageSize,@Param("userId") int userId);//获取正在开会的预约信息

}
