package com.rideshare.rideservice.controller;

import com.rideshare.rideservice.dto.RideRequestDto;
import com.rideshare.rideservice.dto.AcceptRideRequest;
import com.rideshare.rideservice.dto.CompleteRideRequest;
import com.rideshare.rideservice.entity.Ride;
import com.rideshare.rideservice.service.RideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rides")
@Tag(name = "Rides", description = "Ride lifecycle APIs")
public class RideController {
    private final RideService service;

    public RideController(RideService service) {
        this.service = service;
    }

    @Operation(summary = "Request ride", responses = {@ApiResponse(responseCode = "200", description = "Ride created")})
    @PostMapping("/request")
    public Ride request(@Valid @RequestBody RideRequestDto dto) {
        return service.request(dto);
    }

    @Operation(summary = "Accept ride", responses = {@ApiResponse(responseCode = "200", description = "Ride accepted")})
    @PostMapping("/{rideId}/accept")
    public Ride accept(@Parameter(description = "Ride id") @PathVariable Long rideId, @Valid @RequestBody AcceptRideRequest body) {
        return service.accept(rideId, body.getDriverId());
    }

    @Operation(summary = "Start ride")
    @PostMapping("/{rideId}/start")
    public Ride start(@PathVariable Long rideId) {
        return service.start(rideId);
    }

    @Operation(summary = "Complete ride")
    @PostMapping("/{rideId}/complete")
    public Ride complete(@PathVariable Long rideId, @Valid @RequestBody CompleteRideRequest body) {
        return service.complete(rideId, body.getFare());
    }
}
