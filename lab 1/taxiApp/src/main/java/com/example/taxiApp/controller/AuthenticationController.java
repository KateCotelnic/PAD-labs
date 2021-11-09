package com.example.taxiApp.controller;

import com.example.taxiApp.model.ResponseData;
import com.example.taxiApp.model.User;
import com.example.taxiApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthenticationController {
    private UserService userService;

    @PostMapping()
    public ResponseEntity<?> authenticate(@RequestBody User request) {
        System.out.println(request.getUsername());
        return ResponseEntity.ok(ResponseData.builder().username(request.getUsername()).token(userService.getToken(request)).build());
    }
}
