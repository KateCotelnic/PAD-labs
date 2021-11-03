package com.example.cache.controller;

import com.example.cache.cache.CashMap;
import com.example.cache.cache.RequestEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class Controller {
//    int i = 0;
//    @PostMapping("/caching")
//    void add(@RequestBody RequestEntity requestEntity){
//        requestEntity.setTime(LocalDateTime.now());
//        CashMap.cache.put(i++, requestEntity);
//    }
}
