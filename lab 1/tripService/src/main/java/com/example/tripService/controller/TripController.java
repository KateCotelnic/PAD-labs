package com.example.tripService.controller;

import com.example.tripService.dto.TripDTO;
import com.example.tripService.entity.Trip;
import com.example.tripService.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping(
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/currentTrip"
    )
    String post(@ModelAttribute TripDTO tripDTO){
        tripDTO.setTime(tripDTO.getLocation().length() + "");
        tripDTO.setCost(tripDTO.getDestination().length() + "");
        tripService.addTrip(tripDTO);
        return "ok";
    }

    @GetMapping(
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/myTrip/{id}"
    )
    TripDTO get(@PathVariable Long id){
        Trip trip =  tripService.getByUserId(id);
        TripDTO tripDTO = new TripDTO();
        tripDTO.setTripType(trip.getTripType().toString());
        tripDTO.setDestination(trip.getDestination());
        tripDTO.setLocation(trip.getLocation());
        tripDTO.setDriverId(trip.getDriverId().toString());
        tripDTO.setUserId(trip.getUserId().toString());
        return tripDTO;
    }
}
