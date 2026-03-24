package com.rideshare.pricingservice.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PricingResponse {

    private BigDecimal fare;
}
