package com.example.newTripService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PassengerDTO {
    private int pasID;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String card;
    private String points;
    private String phone;
}
