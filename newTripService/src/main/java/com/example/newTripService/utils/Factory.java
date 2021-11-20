package com.example.newTripService.utils;

import com.example.newTripService.entity.User;
import com.example.newTripService.entity.enums.TripType;
import com.example.newTripService.entity.enums.UserType;

import java.util.ArrayList;
import java.util.List;

public class Factory {

    public static List<User> userFactory(){
        ArrayList<User> users = new ArrayList<>();
        users.add(User.builder()
                        .userType(UserType.PASSENGER)
                        .destination("Destination1")
                        .location("Location1")
                        .tripType(TripType.ECONOMIC)
                .build());
        users.add(User.builder()
                .userType(UserType.DRIVER)
                .location("Location2")
                .build());
        return users;
    }
}
