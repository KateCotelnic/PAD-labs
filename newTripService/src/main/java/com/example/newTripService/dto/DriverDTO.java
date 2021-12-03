package com.example.newTripService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DriverDTO {
    private int driverID;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private int car_nr;
    private String color;
    private String points;
    private String phone;
}
