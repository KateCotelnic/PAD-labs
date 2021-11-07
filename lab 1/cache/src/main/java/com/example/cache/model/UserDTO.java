package com.example.cache.model;

import lombok.Data;

@Data
//@AllArgsConstructor
//@Builder
public class UserDTO {
    private String id;
    private String location;
    private String destination;
    private String tripType;
    private String userType;
    private String driverId;
}
