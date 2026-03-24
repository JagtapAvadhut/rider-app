package com.rideshare.matchingservice.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MatchRequest {

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private double lat;

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private double lon;

    @Positive
    private double radiusKm = 5.0;
}
