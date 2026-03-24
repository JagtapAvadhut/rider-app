package com.rideshare.matchingservice.service;

import com.rideshare.matchingservice.dto.*;
import java.util.List;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MatchingService {
    private final StringRedisTemplate redis;

    public MatchingService(StringRedisTemplate redis) {
        this.redis = redis;
    }

    public MatchResponse find(MatchRequest request) {
        var result = redis.opsForGeo().radius(
            "drivers:geo",
            new Circle(new Point(request.getLon(), request.getLat()), new Distance(request.getRadiusKm(), Metrics.KILOMETERS)),
            RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().sortAscending().limit(10)
        );
        if (result == null || result.getContent().isEmpty()) {
            return new MatchResponse(null, "NO_DRIVER_AVAILABLE");
        }

        List<Long> nearestDrivers = result.getContent().stream()
            .map(entry -> Long.valueOf(String.valueOf(entry.getContent().getName())))
            .toList();
        for (Long driverId : nearestDrivers) {
            String availability = redis.opsForValue().get("drivers:availability:" + driverId);
            if (Boolean.parseBoolean(availability)) {
                return new MatchResponse(driverId, "MATCHED");
            }
        }
        return new MatchResponse(null, "NO_DRIVER_AVAILABLE");
    }
}
