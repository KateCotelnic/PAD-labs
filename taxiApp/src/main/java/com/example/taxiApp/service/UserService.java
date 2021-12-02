package com.example.taxiApp.service;

import com.example.taxiApp.model.DriverDTO;
import com.example.taxiApp.model.PassengerDTO;
import com.example.taxiApp.model.User;
import com.example.taxiApp.model.UserDTO;
import com.example.taxiApp.model.UserType;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
@AllArgsConstructor
public class UserService {
    public static String token = "";

    public String getToken(User user) {
        RestTemplate restTemplate = new RestTemplate();
        if (UserType.valueOf(user.getUserType()) == UserType.PASSENGER) {
            String url = "http://test:8080//getPas/";
            ResponseEntity<PassengerDTO> response = restTemplate.getForEntity(url + user.getUsername(), PassengerDTO.class);
            PassengerDTO passengerDTO = response.getBody();
            if (user.getPassword().equals(passengerDTO.getPassword())) {
                token = createJWT(user.getUsername());
            }
        } else {
            String url = "http://test:8080//getDriver/";
            ResponseEntity<DriverDTO> response = restTemplate.getForEntity(url + user.getUsername(), DriverDTO.class);
            DriverDTO driverDTO = response.getBody();
            if (user.getPassword().equals(driverDTO.getPassword())) {
                token = createJWT(user.getUsername());
            }
        }
        if (!token.isEmpty()) {
            sendToken(user.getUsername(), token);
        }
        return token;
    }

    public static String createJWT(String username) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("taxi_app_secret_key");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setId("JWTokenId")
                .setIssuedAt(now)
                .setSubject(username)
                .setIssuer("JWTTOKEN")
                .signWith(signatureAlgorithm, signingKey);

        long expMillis = nowMillis + 600000;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
        return builder.compact();
    }

    private void sendToken(String username, String token) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("username: " + username);
        System.out.println("token: " + token);
        HttpEntity<UserDTO> request = new HttpEntity<>(new UserDTO(username, token));
        try {
            String url = "http://newtrip1:9191/token";
            restTemplate.postForEntity(url, request, Void.class);
        } catch (Exception e){
//            System.out.println("did not send token to 9191");
        }
        try {
            String url = "http://newtrip2:9192/token";
            restTemplate.postForEntity(url, request, Void.class);
        } catch (Exception e){
//            System.out.println("did not send token to 9192");
        }
        try {
            String url = "http://newtrip3:9193/token";
            restTemplate.postForEntity(url, request, Void.class);
        } catch (Exception e){
//            System.out.println("did not send token to 9193");
        }

    }
}
