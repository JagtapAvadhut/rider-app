package com.rideshare.driverservice.controller;

import com.rideshare.driverservice.dto.DriverLocationRequest;
import com.rideshare.driverservice.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drivers")
@Tag(name = "Drivers", description = "Driver APIs")
public class DriverController {
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @Operation(summary = "Update driver location", responses = {
        @ApiResponse(responseCode = "202", description = "Location accepted")
    })
    @PostMapping("/location")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@Valid @RequestBody DriverLocationRequest request) {
        driverService.updateLocation(request);
    }
}
