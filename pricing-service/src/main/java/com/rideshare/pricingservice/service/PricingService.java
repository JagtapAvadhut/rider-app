package com.rideshare.pricingservice.service;

import com.rideshare.pricingservice.dto.PricingRequest;
import com.rideshare.pricingservice.dto.PricingResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    public PricingResponse calculate(PricingRequest request) {
        BigDecimal baseFare = BigDecimal.valueOf(40);
        BigDecimal distanceFare = BigDecimal.valueOf(12).multiply(BigDecimal.valueOf(request.getKm()));
        BigDecimal subtotal = baseFare
            .add(distanceFare)
            .add(BigDecimal.valueOf(request.getWaitingCharge()));

        BigDecimal surgedFare = subtotal.multiply(BigDecimal.valueOf(request.getSurgeMultiplier()));
        BigDecimal finalFare = surgedFare
            .subtract(BigDecimal.valueOf(request.getDiscount()))
            .max(BigDecimal.ZERO)
            .setScale(2, RoundingMode.HALF_UP);

        return new PricingResponse(finalFare);
    }
}
