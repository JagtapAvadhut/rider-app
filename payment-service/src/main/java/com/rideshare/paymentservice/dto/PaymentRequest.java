package com.rideshare.paymentservice.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentRequest {

    @NotNull
    private Long rideId;

    @NotNull
    private BigDecimal amount;
}
