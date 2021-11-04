package com.example.newTripService.controller;

import com.example.newTripService.dto.UserDTO;
import com.example.newTripService.dto.UserRequestDTO;
import com.example.newTripService.entity.User;
import com.example.newTripService.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.ws.rs.HeaderParam;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

@RestController
public class UserController {

//    private final WebClient.Builder userBuilder;

//    public UserController(WebClient.Builder userBuilder) {
//        this.userBuilder = userBuilder;
//    }

    private Map<String,String> tokens = new HashMap<>();

    @Autowired
    private UserService userService;

//        private final Flux <User> userFlux;

//    private final Map<Long, AtomicInteger> countPerSecond = new ConcurrentHashMap<>();

    @PostMapping(
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/newTrip"
    )
    ResponseEntity<UserDTO> post(@RequestHeader("username") String username, @RequestHeader("token") String token, @ModelAttribute UserDTO userDTO){
//        var now = System.currentTimeMillis();
//        var second = (now / 1000);
//        var countForTheCurrentSecond = this.countPerSecond.compute(second, (aLong, atomicInteger) -> {
//            if (atomicInteger == null) {
//                atomicInteger = new AtomicInteger(0);
//            }
//            atomicInteger.incrementAndGet();
//            return atomicInteger;
//        });
//        System.out.println("There have been "+ countForTheCurrentSecond.get() + " requests for the second " + second);
//        System.out.println("username: " + username);
//        System.out.println("token: " + token);
        if(tokens.containsKey(username) && tokens.get(username).equals(token)) {
            return new ResponseEntity<>(userService.addUser(userDTO), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/token")
    void postToken(@RequestBody UserRequestDTO requestDTO){
//        System.out.println("username: " + requestDTO.getUsername());
//        System.out.println("token: " + requestDTO.getToken());
        tokens.put(requestDTO.getUsername(), requestDTO.getToken());
        System.out.println(tokens);
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
