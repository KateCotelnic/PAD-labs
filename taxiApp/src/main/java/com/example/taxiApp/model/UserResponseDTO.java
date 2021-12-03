package com.example.taxiApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String id;
    private String location;
    private String destination;
    private String tripType;
    private String userType;
    private String driverUsername;
}
