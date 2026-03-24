package com.rideshare.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestedEvent {

    private Long rideId;

    private Long riderId;

    private double pickupLat;

    private double pickupLon;

    private double dropLat;

    private double dropLon;
}
