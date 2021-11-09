package com.example.newTripService.repository;

import com.example.newTripService.entity.User;
import com.example.newTripService.entity.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUserType(UserType userType);
}
