package com.example.tripService.utils;

import com.example.tripService.entity.Trip;
import com.example.tripService.entity.enums.PaymentType;
import com.example.tripService.entity.enums.TripType;

import java.util.ArrayList;
import java.util.List;

public class Factory {
    public static List<Trip> tripFactory() {
        ArrayList<Trip> trips = new ArrayList<>();
        trips.add(Trip.builder()
                        .destination("Dest")
                        .id(1L)
                        .driverId(1L)
                        .cost(343.42)
                        .location("Loc")
                        .time("32min")
                        .tripType(TripType.DELIVERY)
                        .paymentType(PaymentType.CARD)
                        .userId(1L)
                .build());
        return trips;
    }
}
