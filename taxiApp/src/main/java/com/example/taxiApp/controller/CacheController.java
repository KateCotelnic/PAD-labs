package com.example.taxiApp.controller;

import com.example.taxiApp.model.UserDTO;
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

import java.util.Objects;

@RestController
public class CacheController {

    RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/new")
    public Object getFromCache(@RequestHeader("username") String username, @RequestHeader("token") String token, @ModelAttribute UserRequestNewDTO userRequestNewDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", token);
        headers.set("username", username);
        HttpEntity<Object> request = new HttpEntity<>(userRequestNewDTO, headers);
        String url = "http://localhost:9393/newTrip";
        try {
            ResponseEntity<Object> response = restTemplate.postForEntity(url, request, Object.class);
            if(response.getBody() == null){
               return secondStep(request, userRequestNewDTO, headers);
            }
            System.out.println("got response from cache 1");
            return response.getBody();
        } catch (Exception e){
          return secondStep(request, userRequestNewDTO, headers);
        }
//            if(response.getStatusCode()==HttpStatus.GATEWAY_TIMEOUT){
//                return new ResponseEntity<>("Service new trip is not available. Try later", HttpStatus.GATEWAY_TIMEOUT);
//            }
//            return response.getBody();
//        } catch (Exception e){
//            return new ResponseEntity<>("Service new trip is not available. Try later", HttpStatus.GATEWAY_TIMEOUT);
//        }
    }

    private Object secondStep(HttpEntity<Object> request, UserRequestNewDTO userRequestNewDTO, HttpHeaders headers){
        String url = "http://localhost:9394/newTrip";
        try {
            ResponseEntity<Object> response = restTemplate.postForEntity(url, request, Object.class);
            if(response.getBody() == null){
                HttpEntity<UserRequestNewDTO> requestNewDTOHttpEntity = new HttpEntity<>(userRequestNewDTO, headers);
                return getResponse(requestNewDTOHttpEntity);
            }
            System.out.println("got response from cache 2");
            return response.getBody();
        } catch (Exception e){
            HttpEntity<UserRequestNewDTO> requestNewDTOHttpEntity = new HttpEntity<>(userRequestNewDTO, headers);
            return getResponse(requestNewDTOHttpEntity);
        }
    }

    private Object getResponse(HttpEntity<UserRequestNewDTO> request){
        Object response;
        int i = 0;
        while (i < 3){
            i++;
            response = getFromService(request, 9191);
            if(Objects.isNull(response)){
                response = getFromService(request, 9192);
                if(Objects.isNull(response)){
                    response = getFromService(request, 9193);
                    if(Objects.isNull(response)){
                        continue;
                    }
                    else {
                        System.out.println("Got response from service 9193");
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                }
                else {
                    System.out.println("Got response from service 9292");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
            else {
                System.out.println("Got response from service 9191");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
    }

    private Object getFromService(HttpEntity<UserRequestNewDTO> request, long port){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:" + port + "/newTrip";
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
