package com.rideshare.rideservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AcceptRideRequest {
    @NotNull
    private Long driverId;
}
