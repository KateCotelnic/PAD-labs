package com.example.newTripService.service;

import com.example.newTripService.dto.UserDTO;
import com.example.newTripService.entity.User;
import com.example.newTripService.entity.enums.TripType;
import com.example.newTripService.entity.enums.UserType;
import com.example.newTripService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void addUser(UserDTO userDTO){
        User user = User.builder()
                .id(Long.parseLong(userDTO.getId()))
                .tripType(TripType.valueOf(userDTO.getTripType()))
                .userType(UserType.valueOf(userDTO.getUserType()))
                .location(userDTO.getLocation())
                .destination(userDTO.getDestination())
                .build();
        userRepository.save(user);
    }

    public User getUser(){
        return userRepository.getById(0L);
    }
}
