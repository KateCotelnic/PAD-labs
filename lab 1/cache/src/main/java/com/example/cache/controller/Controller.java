package com.example.cache.controller;

import com.example.cache.cache.CashMap;
import com.example.cache.cache.RequestEntity;
import com.example.cache.model.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class Controller {

    private

    @PostMapping("/caching")
    void add(@RequestHeader("token") String token, @RequestBody RequestEntity requestEntity) {
        requestEntity.setTime(LocalDateTime.now());
        CashMap.cache.put(token, requestEntity);
        System.out.println(CashMap.cache);
    }

    Object getResponse(String token, UserDTO requestEntity) {
        System.out.println("map : " + CashMap.cache);
        if (!CashMap.cache.containsKey(token)) {
            System.out.println("doesn't contain key");
            return null;
        }
        System.out.println("contains key");
        RequestEntity request = CashMap.cache.get(token);
        String tmp = requestEntity.toString().substring(8);
        String req = "{" + tmp.substring(0, tmp.length() - 1) + "}";
        String ch = request.getBody().toString();
        System.out.println("request: " + req);
        System.out.println("in cache: " + ch);
        if (req.equals(ch)) {
            return request.getResponse();
        }
        return null;
    }

    @PostMapping(
            value = "/newTrip")
    public ResponseEntity<Object> newTrip(@RequestHeader("username") String username, @RequestHeader("token") String token, @ModelAttribute UserDTO userDTO) {
        Object response = getResponse(token, userDTO);
        if (response != null) {
            System.out.println("Got response from cache");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("username", username);
            headers.set("token", token);
            String url = "http://localhost:9191/newTrip";
            HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);
            ResponseEntity<Object> responseFromService = restTemplate.postForEntity(url, request, Object.class);
            Object respons = responseFromService.getBody();
            System.out.println("Got response from service");
            return new ResponseEntity<>(respons, HttpStatus.OK);
        }
    }
}
