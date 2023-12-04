package com.example.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class MeetingReservations {
    int id;
    int userId;
    int roomId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String meetingTopic;

}
