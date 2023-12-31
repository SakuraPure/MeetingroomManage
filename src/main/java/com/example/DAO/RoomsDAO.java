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
    @Select("<script> SELECT * FROM meeting_rooms "+
            "<choose>\n" +
            "  <when test=\"id!= null\">\n" +
            "     WHERE f_id=#{id}\n" +
            "  </when>\n" +
            "  <when test=\"name!=null\">\n"+
            "     WHERE building=#{name}\n"+
            "  </when>\n"+
            "  <when test=\"page!= -1 and pageSize != 0\">\n" +
            "    limit #{page},#{pageSize}\n" +
            "  </when>\n" +
            " </choose>"+
            "</script>")
    List<MeetingRooms> getRoomsAll(@Param("page")int page,@Param("pageSize")int pageSize,@Param("id")Integer id,@Param("name")String name);

    @Results({
            @Result(column = "f_id",property = "id"),
            @Result(column ="building",property = "buildName"),
            @Result(column = "seat_count",property = "capacity"),
            @Result(column = "status",property = "status")
    })
    @Select("select * from meeting_rooms")
    List<MeetingRooms> getRoomsAll2();

    @Results({
            @Result(column = "f_id",property = "id"),
            @Result(column ="building",property = "buildName"),
            @Result(column = "seat_count",property = "capacity"),
            @Result(column = "status",property = "status")
    })
    @Update("<script>update meeting_rooms set "+
            "<if test=\"id!=0\">"+
            "f_id=#{id}, " +
            "</if>"+
            "building=#{buildName},floor=#{floor},seat_count=#{capacity},area=#{area} where f_id=#{oid}"+
            "</script>")
    int updateRoom(@Param("oid")int oid,@Param("id")int id,@Param("buildName")String buildName,@Param("floor")int floor,@Param("capacity")int capacity,@Param("area")double area);

    @Results({
            @Result(column = "f_id",property = "id"),
            @Result(column ="building",property = "buildName"),
            @Result(column = "seat_count",property = "capacity"),
            @Result(column = "status",property = "status")
    })
    @Insert("insert into meeting_rooms(f_id,building,floor,seat_count,area) values(#{id},#{buildName},#{floor},#{capacity},#{area})")
    int insertRoom(MeetingRooms meetingRooms);

    @Results({
            @Result(column = "f_id",property = "id"),
            @Result(column ="building",property = "buildName"),
            @Result(column = "seat_count",property = "capacity"),
            @Result(column = "status",property = "status")
    })
    @Insert("insert into meeting_rooms(building,floor,seat_count,area) values(#{buildName},#{floor},#{capacity},#{area})")
    int insertRoomNoID(MeetingRooms meetingRooms);

    @Results({
            @Result(column = "f_id",property = "id"),
            @Result(column ="building",property = "buildName"),
            @Result(column = "seat_count",property = "capacity"),
            @Result(column = "status",property = "status")
    })
    @Delete("delete from meeting_rooms where f_id=#{id}")
    int deleteRoom(@Param("id")int id);

    @Results({
            @Result(column = "f_id",property = "id"),
            @Result(column ="building",property = "buildName"),
            @Result(column = "seat_count",property = "capacity"),
            @Result(column = "status",property = "status")
    })
    @Update("update meeting_rooms set status=#{status} where f_id=#{id}")
    int updateRoomStatus(@Param("id")int id,@Param("status")String status);

}
