package com.example.cache.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class RequestEntity {

    private String endpoint;
    private Object body;
    private Object response;
    private LocalDateTime time;
}
