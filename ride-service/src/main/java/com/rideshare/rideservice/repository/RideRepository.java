package com.rideshare.rideservice.repository;

import com.rideshare.rideservice.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
