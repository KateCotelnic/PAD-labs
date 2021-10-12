package com.example.tripService.service;

import com.example.tripService.dto.TripDTO;
import com.example.tripService.entity.Trip;
import com.example.tripService.entity.enums.PaymentType;
import com.example.tripService.entity.enums.TripType;
import com.example.tripService.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;

    public void addTrip(TripDTO tripDTO){
        Trip trip = Trip.builder()
                .userId(Long.parseLong(tripDTO.getUserId()))
                .tripType(TripType.valueOf(tripDTO.getTripType()))
                .location(tripDTO.getLocation())
                .driverId(Long.parseLong(tripDTO.getDriverId()))
                .destination(tripDTO.getDestination())
                .build();
        tripRepository.save(trip);
    }

    public Trip getByUserId(Long id){
        return tripRepository.getTripByUserId(id);
    }
}
