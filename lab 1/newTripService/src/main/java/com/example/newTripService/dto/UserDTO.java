package com.example.newTripService.dto;

import com.example.newTripService.entity.enums.TripType;
import com.example.newTripService.entity.enums.UserType;

public class UserDTO {
    private String id;
    private String location;
    private String destination;
    private String tripType;
    private String userType;
    private String driverId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
}
