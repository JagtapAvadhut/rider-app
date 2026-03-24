package com.rideshare.driverservice.service;
import com.rideshare.driverservice.dto.DriverLocationRequest; import org.springframework.data.redis.core.StringRedisTemplate; import org.springframework.kafka.core.KafkaTemplate; import org.springframework.stereotype.Service;
@Service public class DriverService { private final StringRedisTemplate redis; private final KafkaTemplate<String,String> kafka;
 public DriverService(StringRedisTemplate redis, KafkaTemplate<String,String> kafka){this.redis=redis;this.kafka=kafka;}
 public void updateLocation(DriverLocationRequest req){ redis.opsForGeo().add("drivers:geo", new org.springframework.data.geo.Point(req.getLon(), req.getLat()), req.getDriverId().toString()); redis.opsForValue().set("drivers:availability:"+req.getDriverId(), req.getAvailable().toString()); kafka.send("driver.location.updated", req.getDriverId().toString(), req.getLat()+","+req.getLon()); }}
