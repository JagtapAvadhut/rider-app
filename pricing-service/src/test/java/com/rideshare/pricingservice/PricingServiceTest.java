package com.rideshare.pricingservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.rideshare.pricingservice.dto.PricingRequest;
import com.rideshare.pricingservice.service.PricingService;
import org.junit.jupiter.api.Test;

class PricingServiceTest {
    private final PricingService service = new PricingService();

    @Test
    void shouldCalculateFareWithSurgeAndDiscount() {
        PricingRequest request = new PricingRequest();
        request.setKm(10);
        request.setSurgeMultiplier(1.5);
        request.setWaitingCharge(20);
        request.setDiscount(10);

        assertEquals("245.00", service.calculate(request).getFare().toPlainString());
    }
}
