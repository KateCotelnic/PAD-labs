package com.example.newTripService.service;

import com.example.newTripService.dto.UserDTO;
import com.example.newTripService.entity.User;
import com.example.newTripService.entity.enums.TripType;
import com.example.newTripService.entity.enums.UserType;
import com.example.newTripService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO addUser(UserDTO userDTO) {
        User user;
        if (userDTO.getUserType() == "PASSENGER") {
            user = User.builder()
                    .id(Long.parseLong(userDTO.getId()))
                    .tripType(TripType.valueOf(userDTO.getTripType()))
                    .userType(UserType.valueOf(userDTO.getUserType()))
                    .location(userDTO.getLocation())
                    .destination(userDTO.getDestination())
                    .build();
        } else {
            user = User.builder()
                    .id(Long.parseLong(userDTO.getId()))
                    .location(userDTO.getLocation())
                    .build();
        }
        userRepository.save(user);
        userDTO.setDriverId("1");
        return userDTO;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }


    public User getAnyUser() {
        return userRepository.getUserByUserType(UserType.PASSENGER);
    }
}
