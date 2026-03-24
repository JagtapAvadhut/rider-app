package com.rideshare.matchingservice.controller;

import com.rideshare.matchingservice.dto.MatchRequest;
import com.rideshare.matchingservice.dto.MatchResponse;
import com.rideshare.matchingservice.service.MatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matching")
@Tag(name = "Matching", description = "Nearest driver matching APIs")
public class MatchingController {
    private final MatchingService service;

    public MatchingController(MatchingService service) {
        this.service = service;
    }

    @Operation(summary = "Find nearest available driver", responses = {
        @ApiResponse(responseCode = "200", description = "Matched or no driver")
    })
    @PostMapping("/nearest")
    public MatchResponse nearest(@Valid @RequestBody MatchRequest request) {
        return service.find(request);
    }
}
