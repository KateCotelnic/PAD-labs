package com.example.taxiApp.controller;

import com.example.taxiApp.model.UserRequestNewDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CacheController {

    @PostMapping("/newTocache")
    public ResponseEntity<Object> getFromCache(@RequestHeader("username") String username, @RequestHeader("token") String token, @ModelAttribute UserRequestNewDTO userRequestNewDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        System.out.println("username: " + username);
        System.out.println("token: " + token);
        System.out.println("request: " + userRequestNewDTO);
        headers.set("token", token);
        headers.set("username", username);
        HttpEntity<Object> request = new HttpEntity<>(userRequestNewDTO, headers);
        String url = "http://localhost:9393/getResponse";
        ResponseEntity<Object> response;
        try {
            response = restTemplate.postForEntity(url, request, Object.class);
            System.out.println(response);
            System.out.println("status: " + response.getStatusCode());
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Got response from cache");
                return response;
            }
            System.out.println("here");
            url = "http://localhost:9999/newToService";
            response = restTemplate.postForEntity(url, request, Object.class);
            System.out.println("response: " + response);
            System.out.println("Got response from service");
            return response;
        } catch (Exception e) {
            System.out.println("exception");
            headers.set("username", username);
            url = "http://localhost:9191/newTrip";
            response = restTemplate.postForEntity(url, request, Object.class);
            System.out.println(response);
            System.out.println("Got response from service");
            return response;
        }
    }
}
