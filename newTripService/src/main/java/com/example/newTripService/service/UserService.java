package com.example.newTripService.service;

import com.example.newTripService.dto.DriverDTO;
import com.example.newTripService.dto.PassengerDTO;
import com.example.newTripService.dto.UserDTO;
import com.example.newTripService.dto.UserResponseDTO;
import com.example.newTripService.entity.User;
import com.example.newTripService.entity.enums.TripType;
import com.example.newTripService.entity.enums.UserType;
import com.example.newTripService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO addUser(String username, UserDTO userDTO) {
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
        System.out.println("User saved into db");
//        PassengerDTO passengerDTO = null;
//        DriverDTO driverDTO = null;
//        try{
//            RestTemplate restTemplate = new RestTemplate();
//                String url = "http://node:8080//getPas/";
//                ResponseEntity<PassengerDTO> response = restTemplate.getForEntity(url + username, PassengerDTO.class);
//                passengerDTO = response.getBody();
//            System.out.println("passenger: " + passengerDTO);
//                url = "http://node:8080//getDriver/";
//                ResponseEntity<DriverDTO> response2 = restTemplate.getForEntity(url + userDTO.getDriverUsername(), DriverDTO.class);
//                driverDTO = response2.getBody();
//            System.out.println("driver: " + driverDTO);
//            if(passengerDTO==null || driverDTO==null){
//                System.out.println("Rollback. User has been removed from db");
//                userRepository.delete(user);
//            }
//        } catch (Exception e){
//            System.out.println("Rollback. User has been removed from db");
//            userRepository.delete(user);
//        }
//        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
//                .destination(userDTO.getDestination())
//                .id(userDTO.getId())
//                .tripType(userDTO.getTripType())
//                .location(userDTO.getLocation())
//                .me(passengerDTO)
//                .driver(driverDTO)
//                .build();
        userDTO.setDriverUsername("delac");
        return userDTO;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }


    public User getAnyUser() {
        return userRepository.getUserByUserType(UserType.PASSENGER);
    }
}
