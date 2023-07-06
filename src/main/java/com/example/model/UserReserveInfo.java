package com.example.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class UserReserveInfo {
    private int reserveId;//预约id
    private int roomId;//会议室id
    private String buildName;//会议室名字
    private int floor;//会议室楼层
    private LocalDateTime startTime;//会议开始时间
    private LocalDateTime endTime;//结束时间
    private String meetingTopic;//会议主题
    private String status;//预约状态
}
