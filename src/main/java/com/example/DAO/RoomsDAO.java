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
    @Select("SELECT rooms.* ,app.status FROM meeting_rooms as rooms INNER JOIN meeting_reservations as res on rooms.f_id=res.room_id\n" +
            "INNER JOIN meeting_approvals as app on res.f_id=app.reservation_id\n" +
            "WHERE app.status=\"approved\""+
            "LIMIT #{page},#{pageSize}")
    List<MeetingRooms> getRoomsAll(@Param("page")int page,@Param("pageSize")int pageSize);
}
