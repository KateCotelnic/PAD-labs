package com.example.cache.authentification;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseData {
    private String username;
    private String token;
}
