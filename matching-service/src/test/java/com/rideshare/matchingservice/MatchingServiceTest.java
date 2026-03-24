package com.rideshare.matchingservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import com.rideshare.matchingservice.dto.MatchRequest;
import com.rideshare.matchingservice.service.MatchingService;
import org.junit.jupiter.api.Test;
import org.springframework.data.geo.Circle;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

class MatchingServiceTest {
    @SuppressWarnings("unchecked")
    @Test
    void shouldReturnNoDriverWhenRedisReturnsEmpty() {
        StringRedisTemplate redis = mock(StringRedisTemplate.class);
        GeoOperations<String, String> geoOps = mock(GeoOperations.class);
        when(redis.opsForGeo()).thenReturn(geoOps);
        when(geoOps.radius(eq("drivers:geo"), any(Circle.class), any(RedisGeoCommands.GeoRadiusCommandArgs.class)))
            .thenReturn(null);

        MatchingService service = new MatchingService(redis);
        MatchRequest req = new MatchRequest();
        req.setLat(12.0);
        req.setLon(77.0);

        assertEquals("NO_DRIVER_AVAILABLE", service.find(req).getMessage());
    }
}
