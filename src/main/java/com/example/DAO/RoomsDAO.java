package com.example.DAO;

import com.example.model.MeetingRooms;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoomsDAO {
    @Results({
            @Result(column = "f_id",property = "id"),
            @Result(column ="building",property = "buildName"),
            @Result(column = "seat_count",property = "capacity"),
            @Result(column = "status",property = "status")
    })
    @Select("<script> SELECT rooms.* ,app.status FROM meeting_rooms as rooms INNER JOIN meeting_reservations as res on rooms.f_id=res.room_id\n" +
            "INNER JOIN meeting_approvals as app on res.f_id=app.reservation_id\n" +
            "WHERE app.status=\"approved\""+
            "<choose>\n" +
            "  <when test=\"page!= -1 and pageSize != 0\">\n" +
            "    limit #{page},#{pageSize}\n" +
            "  </when>\n" +
            "  <when test=\"id!= null\">\n" +
            "     AND rooms.f_id=#{id}\n" +
            "  </when>\n" +
            "  <when test=\"name!=null\">\n"+
            "     AND rooms.building=#{name}\n"+
            "  </when>\n"+
            " </choose>"+
            "</script>")
    List<MeetingRooms> getRoomsAll(@Param("page")int page,@Param("pageSize")int pageSize,@Param("id")Integer id,@Param("name")String name);

    @Results({
            @Result(column = "f_id",property = "id"),
            @Result(column ="building",property = "buildName"),
            @Result(column = "seat_count",property = "capacity"),
            @Result(column = "status",property = "status")
    })
    @Update("update meeting_rooms set building=#{buildName},floor=#{floor},seat_count=#{capacity},area=#{area} where f_id=#{id}")
    int updateRoom(MeetingRooms meetingRooms);
}
