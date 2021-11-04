package com.example.cache.controller;

import com.example.cache.cache.CashMap;
import com.example.cache.cache.RequestEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class Controller {

    @PostMapping("/caching")
    void add(@RequestHeader("token")String token, @RequestBody RequestEntity requestEntity){
        requestEntity.setTime(LocalDateTime.now());
        CashMap.cache.put(token, requestEntity);
        System.out.println(CashMap.cache);
    }
}
