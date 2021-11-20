package com.example.tripService.service;

import com.example.tripService.entity.Trip;
import com.example.tripService.repository.TripRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.tripService.utils.TripServiceUtils.TRIP;
import static com.example.tripService.utils.TripServiceUtils.TRIP_REQUEST;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    TripService tripService;

    @Test
    void shouldAddTrip(){
        Trip expected = TRIP;
        Trip real = tripService.addTrip(TRIP_REQUEST);
        assertAll(
                () -> assertEquals(expected.getUserId(), real.getUserId()),
                () -> assertEquals(expected.getTripType(), real.getTripType()),
                () -> assertEquals(expected.getLocation(), real.getLocation()),
                () -> assertEquals(expected.getDriverId(), real.getDriverId()),
                () -> assertEquals(expected.getDestination(), real.getDestination()),
                () -> assertEquals(expected.getTime(), real.getTime()),
                () -> assertEquals(expected.getCost(), real.getCost())
                );
    }

    @Test
    void shouldGetTripByUserId(){
        when(tripRepository.getTripByUserId(1L)).thenReturn(TRIP);

        Trip expected = TRIP;
        Trip real = tripService.getByUserId(1L);
        assertAll(
                () -> assertEquals(expected.getUserId(), real.getUserId()),
                () -> assertEquals(expected.getTripType(), real.getTripType()),
                () -> assertEquals(expected.getLocation(), real.getLocation()),
                () -> assertEquals(expected.getDriverId(), real.getDriverId()),
                () -> assertEquals(expected.getDestination(), real.getDestination()),
                () -> assertEquals(expected.getTime(), real.getTime()),
                () -> assertEquals(expected.getCost(), real.getCost())
        );
    }
}
