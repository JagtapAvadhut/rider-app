package com.rideshare.pricingservice.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
public class PricingRequest {

    @DecimalMin("0.0")
    private double km;

    @DecimalMin("0.0")
    private double surgeMultiplier = 1.0;

    @DecimalMin("0.0")
    private double waitingCharge;

    @DecimalMin("0.0")
    private double discount;
}
