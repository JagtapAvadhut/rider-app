package com.rideshare.paymentservice.service;

import com.rideshare.paymentservice.constant.PaymentStatus;
import com.rideshare.paymentservice.dto.PaymentRequest;
import com.rideshare.paymentservice.entity.Payment;
import com.rideshare.paymentservice.repository.PaymentRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository repository;
    private final KafkaTemplate<String, String> kafka;

    public PaymentService(PaymentRepository repository, KafkaTemplate<String, String> kafka) {
        this.repository = repository;
        this.kafka = kafka;
    }

    public Payment process(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setRideId(request.getRideId());
        payment.setAmount(request.getAmount());
        payment.setStatus(PaymentStatus.SUCCEEDED);

        Payment saved = repository.save(payment);
        kafka.send("payment.succeeded", saved.getId().toString(), saved.getRideId().toString());
        return saved;
    }
}
