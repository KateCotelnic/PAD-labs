package com.example.tripService.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import reactor.core.publisher.Flux;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class WebSocketConfiguration {
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
        return new SimpleUrlHandlerMapping(Map.of("/currentTrip", webSocketHandler), 10);
    }
}
