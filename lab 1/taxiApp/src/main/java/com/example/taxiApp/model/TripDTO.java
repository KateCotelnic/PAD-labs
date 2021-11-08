package com.example.taxiApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {
    private String userId;
    private String driverId;
    private String location;
    private String destination;
    private String tripType;
    private String time;
    private String cost;
    private Long port;
}
