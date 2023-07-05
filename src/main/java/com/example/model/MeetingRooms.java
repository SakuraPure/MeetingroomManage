package com.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MeetingRooms {
    int id;
    String buildName;
    int floor;
    double area;
    int capacity;
    String status;
}
