package com.example.tripService.controller;

import com.example.tripService.dto.PaymentDTO;
import com.example.tripService.dto.TripDTO;
import com.example.tripService.entity.Trip;
import com.example.tripService.service.TripService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TripController {

    @Value("${server.port}")
    private Long port;

    @Autowired
    private TripService tripService;

    private final Map<Long, AtomicInteger> countPerSecond = new ConcurrentHashMap<>();

    private final WebClient.Builder builder;

    public TripController(WebClient.Builder builder) {
        this.builder = builder;
    }

    @PostMapping(
            value = "/currentTrip"
    )
    TripDTO post(TripDTO tripDTO) {
        var now = System.currentTimeMillis();
        var second = (now / 1000);
        var countForTheCurrentSecond = this.countPerSecond.compute(second, (aLong, atomicInteger) -> {
            if (atomicInteger == null) {
                atomicInteger = new AtomicInteger(0);
            }
            atomicInteger.incrementAndGet();
            return atomicInteger;
        });
        System.out.println("There have been " + countForTheCurrentSecond.get() + " requests for the second " + second);
        tripDTO.setCost(tripDTO.getDestination().length() + "");
        tripDTO.setTime(tripDTO.getLocation().length() + "");
        tripDTO.setPort(port);
        tripService.addTrip(tripDTO);
        return tripDTO;
    }

    @PostMapping(value = "/payment")
    Trip setPayment(@RequestBody PaymentDTO paymentDTO) {
        tripService.addPaymentType(paymentDTO);
        return tripService.getByUserId(Long.parseLong(paymentDTO.getUserId()));
    }

    @GetMapping(
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
        tripDTO.setCost(tripDTO.getDestination().length() + "");
        tripDTO.setTime(tripDTO.getLocation().length() + "");
        tripDTO.setPort(port);
        return tripDTO;
    }

    private void addCache() {
        builder.build().post().uri("lb://cache/caching");
    }
}
