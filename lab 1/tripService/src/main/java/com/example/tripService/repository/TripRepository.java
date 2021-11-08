package com.example.tripService.repository;

import com.example.tripService.entity.Trip;
import com.example.tripService.entity.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

//    @Query(nativeQuery = true, value = "select t.* from Trip t where t.userId = :id limit 1")
    Trip getTripByUserId(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Trip SET paymentType = :paymentType WHERE id = :id")
    void updateTripById(Long id, PaymentType paymentType);
}
