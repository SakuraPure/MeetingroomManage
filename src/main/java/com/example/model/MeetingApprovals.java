package com.example.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MeetingApprovals {
    int id;
    int reservationId;
    int approverId;//审核人id实际关联的是用户表的id
    String status;//0表示未审核，1表示审核通过，2表示审核未通过
    String comment;

}

