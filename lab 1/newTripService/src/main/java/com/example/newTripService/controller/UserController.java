package com.example.newTripService.controller;

import com.example.newTripService.dto.UserDTO;
import com.example.newTripService.entity.User;
import com.example.newTripService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

//        private final Flux <User> userFlux;

    @PostMapping(
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/newTrip"
    )
    UserDTO post(@ModelAttribute UserDTO userDTO){
        return userService.addUser(userDTO);
    }
//    @GetMapping(
//            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
//            value = "/newTrip"
//    )
//    Flux <User> get(){
//        return this.userFlux;
//    }
//    @RequestMapping(value = "/newTrip", method = RequestMethod.POST)
//    public String newUser(@ModelAttribute("userUpdateDTO") UserDTO userDTO) {
//        userService.addUser(userDTO);
//        return "user added";
//    }
//    @RequestMapping(value = "/newTrip", method = RequestMethod.GET)
//    public User getUser() {
//        return userService.getUser();
//    }
}
