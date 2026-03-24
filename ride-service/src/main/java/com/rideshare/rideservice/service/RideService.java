package com.rideshare.rideservice.service;

import com.rideshare.rideservice.constant.RideStatus;
import com.rideshare.rideservice.dto.RideRequestDto;
import com.rideshare.rideservice.entity.Ride;
import com.rideshare.rideservice.repository.RideRepository;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RideService {

    private final RideRepository rideRepository;
    private final KafkaTemplate<String, String> kafka;

    public RideService(RideRepository rideRepository, KafkaTemplate<String, String> kafka) {
        this.rideRepository = rideRepository;
        this.kafka = kafka;
    }

    public Ride request(RideRequestDto request) {
        Ride ride = new Ride();
        ride.setRiderId(request.getRiderId());
        ride.setPickupLat(request.getPickupLat());
        ride.setPickupLon(request.getPickupLon());
        ride.setDropLat(request.getDropLat());
        ride.setDropLon(request.getDropLon());
        ride.setStatus(RideStatus.REQUESTED);

        Ride saved = rideRepository.save(ride);
        kafka.send("ride.requested", saved.getId().toString(), "rideRequested");
        return saved;
    }

    public Ride accept(Long rideId, Long driverId) {
        Ride ride = rideRepository.findById(rideId)
            .orElseThrow(() -> new NoSuchElementException("Ride not found: " + rideId));
        if (driverId == null) {
            throw new IllegalArgumentException("driverId is required");
        }
        ride.setDriverId(driverId);
        ride.setStatus(RideStatus.ACCEPTED);

        Ride saved = rideRepository.save(ride);
        kafka.send("ride.accepted", rideId.toString(), "accepted");
        return saved;
    }

    public Ride start(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
            .orElseThrow(() -> new NoSuchElementException("Ride not found: " + rideId));
        ride.setStatus(RideStatus.STARTED);
        return rideRepository.save(ride);
    }

    public Ride complete(Long rideId, BigDecimal fare) {
        Ride ride = rideRepository.findById(rideId)
            .orElseThrow(() -> new NoSuchElementException("Ride not found: " + rideId));
        if (fare == null || fare.signum() < 0) {
            throw new IllegalArgumentException("fare must be non-negative");
        }
        ride.setStatus(RideStatus.COMPLETED);
        ride.setFare(fare);

        Ride saved = rideRepository.save(ride);
        kafka.send("ride.completed", rideId.toString(), fare.toPlainString());
        return saved;
    }
}
