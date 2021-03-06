package com.example.tripService.entity;

import com.example.tripService.entity.enums.PaymentType;
import com.example.tripService.entity.enums.TripType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @Column(unique = true)
    private Long userId;
    private Long driverId;
    private String location;
    private String destination;
    private TripType tripType;
    private PaymentType paymentType;
    private String time;
    private Double cost;
}
