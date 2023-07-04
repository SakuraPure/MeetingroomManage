package com.example.model;

import lombok.Data;

@Data
public class MeetingRooms {
    int id;
    String buildName;
    int floor;
    float area;
    int capacity;
    String status;
}
