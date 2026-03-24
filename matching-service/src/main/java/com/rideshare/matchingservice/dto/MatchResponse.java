package com.rideshare.matchingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchResponse {

    private Long driverId;

    private String message;
}
