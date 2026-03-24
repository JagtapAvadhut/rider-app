package com.rideshare.trackingservice.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/internal/health")
    public Map<String, String> health() {
        return Map.of("service", "tracking-service", "status", "UP");
    }
}
