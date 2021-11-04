package com.example.newTripService.config;

import com.example.newTripService.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
