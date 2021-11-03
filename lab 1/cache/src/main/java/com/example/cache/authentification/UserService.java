package com.example.cache.authentification;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class UserService {

    public String getToken(User user) throws IOException {
//        CloseableHttpClient client = HttpClientBuilder.create().build();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080//getPas/";
        String token = "";
        if(UserType.valueOf(user.getUserType())== UserType.DRIVER){
            ResponseEntity<PassengerDTO> response = restTemplate.getForEntity(url + user.getUsername(), PassengerDTO.class);
            PassengerDTO passengerDTO = response.getBody();
            System.out.println(passengerDTO);
//            HttpGet request = new HttpGet("http://localhost:8080//getPas/" + user.getUsername());
//            HttpResponse response = client.execute(request);
//            HttpEntity entity = response.getEntity();
//            System.out.println(entity);
        }
        else {

        }
        return token;
    }
}
