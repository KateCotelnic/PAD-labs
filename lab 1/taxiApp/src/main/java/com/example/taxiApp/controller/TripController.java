package com.example.taxiApp.controller;

import com.example.taxiApp.model.TripRequestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TripController {

    int i = 0;

    @GetMapping("/myTrip/{id}")
    public TripRequestDTO getMyTrip(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        i++;
        if (i % 2 == 0)
            return restTemplate.getForObject("http://localhost:9292/myTrip/" + id, TripRequestDTO.class);
        else
            return restTemplate.getForObject("http://localhost:9293/myTrip/" + id, TripRequestDTO.class);
    }
}
