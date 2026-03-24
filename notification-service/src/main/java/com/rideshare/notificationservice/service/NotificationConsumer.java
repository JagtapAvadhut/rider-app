package com.rideshare.notificationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);

    @KafkaListener(topics = {"ride.requested", "ride.accepted", "ride.completed", "payment.succeeded"}, groupId = "notification-service")
    public void consume(String event) {
        log.info("Notification event received: {}", event);
    }
}
