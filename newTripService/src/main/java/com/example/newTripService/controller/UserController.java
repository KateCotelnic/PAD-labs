package com.example.newTripService.controller;

import com.example.newTripService.config.WebSocketConfiguration;
import com.example.newTripService.dto.CacheDTO;
import com.example.newTripService.dto.UserDTO;
import com.example.newTripService.dto.UserRequestDTO;
import com.example.newTripService.entity.User;
import com.example.newTripService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private Map<String, String> tokens = new HashMap<>();

    @Autowired
    private UserService userService;

    @Autowired
    WebSocketConfiguration configuration;

    @PostMapping(
            value = "/newTrip"
    )
    UserDTO post(@RequestHeader("username") String username, @RequestHeader("token") String token, @RequestBody UserDTO userDTO) {
        if (tokens.containsKey(username) && tokens.get(username).equals(token)) {
            RestTemplate restTemplate = configuration.getRestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("token", token);
//            System.out.println(userDTO);
            UserDTO userDTOSend = UserDTO.builder()
                    .id(userDTO.getId())
                    .destination(userDTO.getDestination())
                    .userType(userDTO.getUserType())
                    .location(userDTO.getLocation())
                    .tripType(userDTO.getTripType())
                    .build();
            UserDTO user = userService.addUser(userDTO);
            CacheDTO cacheDTO = CacheDTO.builder()
                    .endpoint("http://localhost:9999/new")
                    .body(userDTOSend)
                    .response(new ResponseEntity<>(user, HttpStatus.OK))
                    .build();
            HttpEntity<CacheDTO> request = new HttpEntity<>(cacheDTO, headers);
            String url = "http://localhost:9393/caching";
            try {
                restTemplate.postForEntity(url, request, Void.class);
            } catch (Exception e){
//                System.out.println(e.getMessage());
                System.out.println("Not insert in cache");
            }
//            System.out.println(request);
//            System.out.println("user response: " + user);
            return user;
        }
        return null;
    }

    @PostMapping("/token")
    void postToken(@RequestBody UserRequestDTO requestDTO) {
        tokens.put(requestDTO.getUsername(), requestDTO.getToken());
//        System.out.println(tokens);
    }

    @GetMapping("/getAllFromNewTrip")
    List<User> getAll() {
        return userService.getAll();
    }
}
