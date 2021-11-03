package com.example.cache.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.servlet.server.Session;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class RequestEntity {

    private String endpoint;
    private String token;
    private Object body;
    private Object response;
    private LocalDateTime time;
}
