package com.rideshare.rideservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CompleteRideRequest {
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal fare;
}
