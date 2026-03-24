package com.rideshare.paymentservice.controller;

import com.rideshare.paymentservice.dto.PaymentRequest;
import com.rideshare.paymentservice.entity.Payment;
import com.rideshare.paymentservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@Tag(name = "Payments", description = "Payment processing APIs")
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @Operation(summary = "Process payment", responses = {
        @ApiResponse(responseCode = "200", description = "Payment processed")
    })
    @PostMapping("/process")
    public Payment process(@Valid @RequestBody PaymentRequest request) {
        return service.process(request);
    }
}
