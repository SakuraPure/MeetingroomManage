package com.example.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingReservations {
    int id;
    int userId;
    int roomId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String meetingTopic;

}
