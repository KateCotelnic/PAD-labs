package com.example.taxiApp.service;

import com.example.taxiApp.model.*;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.netty.handler.codec.http.HttpRequest;
import lombok.AllArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.netty.http.client.HttpClient;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.sql.Driver;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    public static String token = "";

    public String getToken(User user){
        RestTemplate restTemplate = new RestTemplate();
        if(UserType.valueOf(user.getUserType())== UserType.PASSENGER){
            String url = "http://localhost:8080//getPas/";
            ResponseEntity<PassengerDTO> response = restTemplate.getForEntity(url + user.getUsername(), PassengerDTO.class);
            PassengerDTO passengerDTO = response.getBody();
            if(user.getPassword().equals(passengerDTO.getPassword())){
                token = createJWT(user.getUsername());
            }
        }
        else {
            String url = "http://localhost:8080//getDriver/";
            ResponseEntity<DriverDTO> response = restTemplate.getForEntity(url + user.getUsername(), DriverDTO.class);
            DriverDTO driverDTO = response.getBody();
            if(user.getPassword().equals(driverDTO.getPassword())){
                token = createJWT(user.getUsername());
            }
        }
        if(!token.isEmpty()){
            sendToken(token);
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

    private void sendToken(String token){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("username", token);
        map.put("token", token);
        String url = "http://localhost:9191/token";
        restTemplate.postForEntity(url, map, Void.class);
//        url = "http://localhost:9292/token";
//        restTemplate.postForEntity(url, map, Void.class);
    }
}
