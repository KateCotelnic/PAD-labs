package com.example.tripService.controller;

import com.example.tripService.dto.TripDTO;
import com.example.tripService.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
        tripDTO.setCost((double) tripDTO.getDestination().length());
        tripService.addTrip(tripDTO);
        return "ok";
    }
}
