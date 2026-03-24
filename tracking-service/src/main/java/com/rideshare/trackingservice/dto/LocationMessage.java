package com.rideshare.trackingservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LocationMessage {
    @NotNull
    private Long rideId;
    @NotNull
    private Long driverId;
    private double lat;
    private double lon;
}
