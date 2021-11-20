package com.example.tripService.utils;

import com.example.tripService.dto.TripDTO;
import com.example.tripService.entity.Trip;
import com.example.tripService.entity.enums.TripType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TripServiceUtils {

    public static final TripDTO TRIP_REQUEST = TripDTO.builder()
            .destination("destination")
            .location("location")
            .userId("1")
            .tripType("PREMIUM")
            .driverId("1")
            .time("10 min")
            .cost("24.60")
            .build();

    public static final Trip TRIP = Trip.builder()
            .userId(1L)
            .tripType(TripType.PREMIUM)
            .location("location")
            .driverId(1L)
            .destination("destination")
            .time("10 min")
            .cost(24.60)
            .build();

    public static final TripDTO TRIP_RESPONSE = TripDTO.builder()
            .driverId("1")
            .userId("1")
            .destination("destination")
            .location("location")
            .tripType("PREMIUM")
            .time("3")
            .cost("4")
            .port(9292L)
            .build();
}
