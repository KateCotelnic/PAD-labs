package com.example.newTripService.service;

import com.example.newTripService.entity.User;
import com.example.newTripService.repository.UserRepository;
import com.example.newTripService.utils.Factory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsertEntitiesService {
    private final UserRepository userRepository;

    public void insertEntities(){
        saveUsers();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void saveUsers(){
        List<User> users = Factory.userFactory();

        userRepository.saveAll(users);
    }
}
