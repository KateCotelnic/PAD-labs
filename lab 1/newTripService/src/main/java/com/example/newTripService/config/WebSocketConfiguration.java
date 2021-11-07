package com.example.newTripService.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class WebSocketConfiguration {

//    private final ObjectMapper objectMapper;
//
//    @SneakyThrows
//    private String from (User user){
//        return this.objectMapper.writeValueAsString(user);
//    }

    @Bean
    public RestTemplate getRestTemplate()
    {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(1);
        httpRequestFactory.setConnectTimeout(1);
        httpRequestFactory.setReadTimeout(1);

        return new RestTemplate(httpRequestFactory);
    }

    @Bean
    WebSocketHandler webSocketHandler(){
        return  webSocketSession -> {
//                var map = userFlux
//                        .map(user -> from(user))
//                        .map(webSocketSession::textMessage);
                return webSocketSession.receive().then();
            };
        }

        @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler webSocketHandler){
        return new SimpleUrlHandlerMapping(Map.of("/newTrip", webSocketHandler), 10);
        }
    }
