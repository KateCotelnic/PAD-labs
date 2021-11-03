package com.example.taxiApp.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseData {
    private String username;
    private String token;
}
