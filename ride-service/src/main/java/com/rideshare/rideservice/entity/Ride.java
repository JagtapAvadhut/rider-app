package com.rideshare.rideservice.entity;

import com.rideshare.rideservice.constant.RideStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "rides",
    indexes = {
        @Index(name = "idx_rides_rider_id", columnList = "riderId"),
        @Index(name = "idx_rides_driver_id", columnList = "driverId"),
        @Index(name = "idx_rides_status", columnList = "status")
    }
)
@Getter
@Setter
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long riderId;
    private Long driverId;
    private double pickupLat;
    private double pickupLon;
    private double dropLat;
    private double dropLon;

    @Enumerated(EnumType.STRING)
    private RideStatus status;

    private BigDecimal fare;
}
