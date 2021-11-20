package com.example.newTripService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {
    private String id;
    private String location;
    private String destination;
    private String tripType;
    private String userType;
    private String driverId;
}
