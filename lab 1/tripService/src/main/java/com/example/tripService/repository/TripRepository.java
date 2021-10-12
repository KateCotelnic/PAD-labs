package com.example.tripService.repository;

import com.example.tripService.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    Trip getTripByUserId(Long id);
}
