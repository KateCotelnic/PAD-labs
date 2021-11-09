package com.example.tripService.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class WebSocketConfiguration {
    @Bean
    WebSocketHandler webSocketHandler() {
        return webSocketSession -> {
            return webSocketSession.receive().then();
        };
    }

    @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler webSocketHandler) {
        return new SimpleUrlHandlerMapping(Map.of("/currentTrip", webSocketHandler), 10);
    }

//    private final ObjectMapper objectMapper;
//
//    @SneakyThrows
//    private String from (Trip trip){
//        return this.objectMapper.writeValueAsString(trip);
//    }

//    @Bean
//    WebSocketHandler webSocketHandler1(){
//        return  webSocketSession -> {
////                var map = userFlux
////                        .map(user -> from(user))
////                        .map(webSocketSession::textMessage);
//            return webSocketSession.send();
//        };
//    }
//
//    @Bean
//    SimpleUrlHandlerMapping simpleUrlHandlerMapping1(WebSocketHandler webSocketHandler1){
//        return new SimpleUrlHandlerMapping(Map.of("/myTrip", webSocketHandler1), 10);
//    }
//}
}
