package com.rideshare.trackingservice.controller;

import com.rideshare.trackingservice.dto.LocationMessage;
import com.rideshare.trackingservice.dto.RideStatusMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracking")
@Tag(name = "Tracking", description = "REST + WebSocket tracking APIs")
public class TrackingController {

    private final SimpMessagingTemplate messagingTemplate;

    public TrackingController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Operation(summary = "WebSocket subscription guide")
    @GetMapping("/subscribe")
    public String subscribe() {
        return "Subscribe topics: /topic/ride/{rideId}/location and /topic/ride/{rideId}/status. Send to /app/driver/location/{rideId} or /app/ride/status/{rideId}.";
    }

    @MessageMapping("/driver/location/{rideId}")
    public void publishLocation(@DestinationVariable Long rideId, @Valid LocationMessage message) {
        messagingTemplate.convertAndSend("/topic/ride/" + rideId + "/location", message);
    }

    @MessageMapping("/ride/status/{rideId}")
    public void publishRideStatus(@DestinationVariable Long rideId, @Valid RideStatusMessage message) {
        messagingTemplate.convertAndSend("/topic/ride/" + rideId + "/status", message);
    }
}
