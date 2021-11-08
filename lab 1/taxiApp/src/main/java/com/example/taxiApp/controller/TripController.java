package com.example.taxiApp.controller;

import com.example.taxiApp.model.TripDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TripController {

//    @Autowired
//    private RestTemplate restTemplate;

    int i = 0;

    @GetMapping("/myTrip/{id}")
    public TripDTO getMyTrip(@PathVariable Long id){
        RestTemplate restTemplate = new RestTemplate();
        i++;
        if(i%2==0)
            return restTemplate.getForObject("http://localhost:9292/myTrip/" + id, TripDTO.class);
        else
            return restTemplate.getForObject("http://localhost:9293/myTrip/" + id, TripDTO.class);
    }
}
