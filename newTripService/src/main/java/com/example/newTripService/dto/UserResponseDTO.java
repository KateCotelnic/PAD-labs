package com.example.newTripService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private String id;
    private String location;
    private String destination;
    private String tripType;
    private PassengerDTO me;
    private DriverDTO driver;
}
