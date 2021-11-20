package com.example.taxiApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestNewDTO {
    private String id;
    private String location;
    private String destination;
    private String tripType;
    private String userType;
    private String driverId;
}