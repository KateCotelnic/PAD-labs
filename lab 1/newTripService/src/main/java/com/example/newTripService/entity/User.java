package com.example.newTripService.entity;

import com.example.newTripService.entity.enums.TripType;
import com.example.newTripService.entity.enums.UserType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class User{
    @Id
    private Long id;
    private String location;
    private String destination;
    private TripType tripType;
    private UserType userType;
    private Long driverId;
}
