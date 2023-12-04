package com.example.DAO;


import com.example.model.MeetingApprovals;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ApprovalsDAO {
    @Results({
            @Result(column = "f_id",property = "id"),
            @Result(column = "reservation_id",property = "reservationId"),
            @Result(column = "approver_id",property = "approverId"),
            @Result(column = "status",property = "status"),
            @Result(column = "comments",property = "comment"),
    })
    @Insert("insert into meeting_approvals(reservation_id,approver_id,status,comments) values(#{reservationId},#{approverId},#{status},#{comment})")
    int insertApprovals(MeetingApprovals meetingApprovals);

    @Results({
            @Result(column = "f_id",property = "id"),
            @Result(column = "reservation_id",property = "reservationId"),
            @Result(column = "approver_id",property = "approverId"),
            @Result(column = "status",property = "status"),
            @Result(column = "comments",property = "comment"),
    })
    @Select("select * from meeting_approvals where reservation_id=#{reservationId}")
    MeetingApprovals getApprovalsByReservationId(@Param("reservationId")int reservationId);

    @Results({
            @Result(column = "f_id",property = "id"),
            @Result(column = "reservation_id",property = "reservationId"),
            @Result(column = "approver_id",property = "approverId"),
            @Result(column = "status",property = "status"),
            @Result(column = "comments",property = "comment"),
    })
    @Delete("delete from meeting_approvals where reservation_id=#{reservationId}")
    int deleteApprovalsByReservationId(@Param("reservationId")int reservationId);
}
