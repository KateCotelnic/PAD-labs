package com.example.client;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClientApplication.class, args);

        RestTemplate restTemplate = new RestTemplate();

        for(int i = 0; i < 16; i++){
            TripDTO tripDTO = TripDTO.builder()
                    .userId("10")
                    .location("loc")
                    .destination("dest")
                    .tripType("PREMIUM")
                    .driverId("1")
                    .build();

            String plainCreds = "gateway:taxi";
            byte[] plainCredsBytes = plainCreds.getBytes();
            byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
            String base64Creds = new String(base64CredsBytes);

//        String authStr = "gateway:taxi";
//        String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Basic " + base64Creds);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);

            HttpEntity<TripDTO> request = new HttpEntity<>(tripDTO, headers);
            TripDTO response = restTemplate.postForEntity("http://localhost:9999/trip", request, TripDTO.class).getBody();
            System.out.println(response);
            System.out.println("\n");
        }
    }

}
