package com.example.newTripService.controller;

import com.example.newTripService.entity.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
