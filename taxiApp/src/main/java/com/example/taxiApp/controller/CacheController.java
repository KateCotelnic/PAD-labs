package com.example.taxiApp.controller;

import com.example.taxiApp.model.UserDTO;
import com.example.taxiApp.model.UserRequestNewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@Slf4j
public class CacheController {

    RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/new")
    public Object getFromCache(@RequestHeader("username") String username, @RequestHeader("token") String token, @ModelAttribute UserRequestNewDTO userRequestNewDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", token);
        headers.set("username", username);
        HttpEntity<Object> request = new HttpEntity<>(userRequestNewDTO, headers);
        String url = "http://cache1:9393/newTrip";
        try {
            ResponseEntity<Object> response = restTemplate.postForEntity(url, request, Object.class);
            System.out.println("response from cache 1: " + response);
            if(response.getBody() == null){
               return secondStep(request, userRequestNewDTO, headers);
            }
            log.info("got response from cache 1");
            return response.getBody();
        } catch (Exception e){
          return secondStep(request, userRequestNewDTO, headers);
        }
    }

    private Object secondStep(HttpEntity<Object> request, UserRequestNewDTO userRequestNewDTO, HttpHeaders headers){
        String url = "http://cache2:9394/newTrip";
        try {
            ResponseEntity<Object> response = restTemplate.postForEntity(url, request, Object.class);
            System.out.println("response from cache 2: " + response);
            if(response.getBody() == null){
                HttpEntity<UserRequestNewDTO> requestNewDTOHttpEntity = new HttpEntity<>(userRequestNewDTO, headers);
                return getResponse(requestNewDTOHttpEntity);
            }
            log.info("got response from cache 2");
            return response.getBody();
        } catch (Exception e){
            HttpEntity<UserRequestNewDTO> requestNewDTOHttpEntity = new HttpEntity<>(userRequestNewDTO, headers);
            return getResponse(requestNewDTOHttpEntity);
        }
    }


    private Object getResponse(HttpEntity<UserRequestNewDTO> request){
        Object response;
        LocalDateTime start = LocalDateTime.now();
        while (true){
            if(LocalDateTime.now().isAfter(start.plusSeconds(CircuitBreaker.timeoutSeconds))){
                return new ResponseEntity<>("Gateway timeout! Service 'new trip' is not available.", HttpStatus.GATEWAY_TIMEOUT);
            }
            response = getFromService(request, 1, 9191);
            if(Objects.isNull(response)){
                response = getFromService(request, 2, 9192);
                if(Objects.isNull(response)){
                    response = getFromService(request, 3, 9193);
                    if(Objects.isNull(response)){
                        continue;
                    }
                    else {
                        log.info("Got response from service 9193");
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                }
                else {
                    log.info("Got response from service 9292");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
            else {
                log.info("Got response from service 9191");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
//        return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
    }

    private Object getFromService(HttpEntity<UserRequestNewDTO> request, int trip, long port){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://newtrip" + trip + ":" + port + "/newTrip";
        try {
            ResponseEntity<Object> responseFromService = restTemplate.postForEntity(url, request, Object.class);
            return responseFromService.getBody();
        }catch (Exception e){
            return null;
        }
    }
//        try {
//            response = restTemplate.postForEntity(url, request, Object.class);
//            System.out.println(response);
//            System.out.println("status: " + response.getStatusCode());
//            if (response.getStatusCode() == HttpStatus.OK) {
//                System.out.println("Got response from cache");
//                return response;
//            }
//            System.out.println("here");
//            url = "http://localhost:9999/newToService";
//            response = restTemplate.postForEntity(url, request, Object.class);
//            System.out.println("response: " + response);
//            System.out.println("Got response from service");
//            return response;
//        } catch (Exception e) {
//            System.out.println("exception");
//            headers.set("username", username);
//            url = "http://localhost:9191/newTrip";
//            response = restTemplate.postForEntity(url, request, Object.class);
//            System.out.println(response);
//            System.out.println("Got response from service");
//            return response;
//        }
//    }
}
