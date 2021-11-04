package com.example.newTripService.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CashDTO {
    private String endpoint;
    private Object body;
    private Object response;
    private LocalDateTime time;
}
