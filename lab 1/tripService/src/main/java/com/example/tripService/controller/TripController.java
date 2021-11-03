package com.example.tripService.controller;

import com.example.tripService.dto.PaymentDTO;
import com.example.tripService.dto.TripDTO;
import com.example.tripService.entity.Trip;
import com.example.tripService.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class TripController {

    @Autowired
    private TripService tripService;

    private final WebClient.Builder builder;

    public TripController(WebClient.Builder builder) {
        this.builder = builder;
    }

    @PostMapping(
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/currentTrip"
    )
    TripDTO post(@ModelAttribute TripDTO tripDTO) {
        tripDTO.setTime(tripDTO.getLocation().length() + "");
        tripDTO.setCost(tripDTO.getDestination().length() + "");
        tripService.addTrip(tripDTO);
        return tripDTO;
    }

    @PostMapping(value = "/payment")
    Trip setPayment(@RequestBody PaymentDTO paymentDTO) {
        tripService.addPaymentType(paymentDTO);
        return tripService.getByUserId(Long.parseLong(paymentDTO.getUserId()));
    }

    @GetMapping(
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/myTrip/{id}"
    )
    TripDTO get(@PathVariable Long id) {
        Trip trip = tripService.getByUserId(id);
        TripDTO tripDTO = new TripDTO();
        tripDTO.setTripType(trip.getTripType().toString());
        tripDTO.setDestination(trip.getDestination());
        tripDTO.setLocation(trip.getLocation());
        tripDTO.setDriverId(trip.getDriverId().toString());
        tripDTO.setUserId(trip.getUserId().toString());
        return tripDTO;
    }

    private void addCache() {
        builder.build().post().uri("lb://cache/caching");
    }
}
