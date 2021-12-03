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
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<UserResponseDTO> addUser(String username, UserDTO userDTO) {
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
        log.info("User saved into database");
        PassengerDTO passengerDTO = PassengerDTO.builder()
                .pasID(10)
                .firstname("Kate")
                .lastname("Cot")
                .card("098890098")
                .points("1")
                .phone("066666666")
                .username("kk")
                .password("kkpas")
                .build();
        DriverDTO driverDTO = DriverDTO.builder()
                .driverID(1)
                .firstname("Rochette")
                .lastname("Delacourt")
                .car_nr(158)
                .color("yellow")
                .points("1932")
                .phone("640010424")
                .username("delac")
                .password("roch")
                .build();
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
//                log.info("Rollback. User has been removed from db");
//                userRepository.delete(user);
//                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
//            }
//        } catch (Exception e){
//            log.info("Cached Exception. Rollback. User has been removed from db");
//            userRepository.delete(user);
//            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
//        }
        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .destination(userDTO.getDestination())
                .id(userDTO.getId())
                .tripType(userDTO.getTripType())
                .location(userDTO.getLocation())
                .me(passengerDTO)
                .driver(driverDTO)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }


    public User getAnyUser() {
        return userRepository.getUserByUserType(UserType.PASSENGER);
    }
}
