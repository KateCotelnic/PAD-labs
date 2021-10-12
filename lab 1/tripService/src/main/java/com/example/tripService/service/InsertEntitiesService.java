package com.example.tripService.service;

import com.example.tripService.entity.Trip;
import com.example.tripService.repository.TripRepository;
import com.example.tripService.utils.Factory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsertEntitiesService {
    private final TripRepository tripRepository;

    public void insertEntities(){
        saveTrips();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void saveTrips(){
        List<Trip> trips = Factory.tripFactory();

        tripRepository.saveAll(trips);
    }
}
