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
        user = User.builder()
                .location("driver loc")
                .id(10L)
                .build();
        userRepository.save(user);
//        System.out.println(userDTO);
//        System.out.println(userDTO.getUserType());
        if (userDTO.getUserType().equals("PASSENGER")) {
//            System.out.println("pas");
            user = User.builder()
                    .id(Long.parseLong(userDTO.getId()))
                    .tripType(TripType.valueOf(userDTO.getTripType()))
                    .userType(UserType.valueOf(userDTO.getUserType()))
                    .location(userDTO.getLocation())
                    .destination(userDTO.getDestination())
                    .build();
            user.setDriverId(10L);
            System.out.println();
            System.out.println(user);
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
