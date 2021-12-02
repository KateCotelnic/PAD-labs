package com.example.taxiApp.controller;

import com.example.taxiApp.model.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/status")
public class StatusController {

    @GetMapping
    public Status getStatus() {
        return new Status(HttpStatus.OK);
    }

    @GetMapping("/badreq")
    public Status getError() {
        return new Status(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/new")
    public ResponseEntity<Status> getStatusNew() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://newtrip1:9191/status";
        ResponseEntity<Status> response;
        try {
            response = restTemplate.getForEntity(url, Status.class);
        } catch (Exception e) {
            response = new ResponseEntity<>(new Status(HttpStatus.LOCKED), HttpStatus.OK);
        }
        return response;
    }
}
