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

    //  @PostMapping("/getResponse")
    Object getResponse(String token, UserDTO requestEntity) {
//        System.out.println("request: " + requestEntity);
        System.out.println("map : " + CashMap.cache);
//        System.out.println("token: " + token);
        if (!CashMap.cache.containsKey(token)) {
            System.out.println("doesn't contain key");
//            System.out.println(new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));
            return null;
        }
//        UserDTO userDTO = (UserDTO) requestEntity;
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
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/newTrip")
    public ResponseEntity<Object> newTrip(@RequestHeader("username") String username, @RequestHeader("token") String token, @ModelAttribute UserDTO userDTO) {
//        System.out.println("username: " + username);
//        System.out.println("token: " + token);
//        System.out.println("request: " + userDTO);
//        headers.set("token", token);
//        headers.set("username", username);
//        HttpEntity<Object> request = new HttpEntity<>(userRequestNewDTO, headers);
//        String url = "http://localhost:9393/getResponse";
        Object response = getResponse(token, userDTO);
//        response = restTemplate.postForEntity(url, request, Object.class);
//        System.out.println(response);
        if (response != null) {
            System.out.println("Got response from cache");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
//        System.out.println("here");
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("username", username);
            headers.set("token", token);
            String url = "http://localhost:9191/newTrip";
            HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);
            ResponseEntity<Object> responseFromService = restTemplate.postForEntity(url, request, Object.class);
//                .exchange(url, HttpMethod.POST, request, Void.class);
//        System.out.println("response: " + responseFromService);
            Object respons = responseFromService.getBody();
            System.out.println("Got response from service");
            return new ResponseEntity<>(respons, HttpStatus.OK);
        }
    }
}
