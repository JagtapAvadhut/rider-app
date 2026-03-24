package com.rideshare.pricingservice.controller;

import com.rideshare.pricingservice.dto.PricingRequest;
import com.rideshare.pricingservice.dto.PricingResponse;
import com.rideshare.pricingservice.service.PricingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pricing")
@Tag(name = "Pricing", description = "Fare calculation APIs")
public class PricingController {
    private final PricingService service;

    public PricingController(PricingService service) {
        this.service = service;
    }

    @Operation(summary = "Calculate fare", responses = {@ApiResponse(responseCode = "200", description = "Fare calculated")})
    @PostMapping("/calculate")
    public PricingResponse calculate(@Valid @RequestBody PricingRequest request) {
        return service.calculate(request);
    }
}
