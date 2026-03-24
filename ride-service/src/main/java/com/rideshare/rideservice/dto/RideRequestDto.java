package com.rideshare.rideservice.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RideRequestDto {
    @NotNull
    private Long riderId;

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private double pickupLat;

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private double pickupLon;

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private double dropLat;

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private double dropLon;
}
