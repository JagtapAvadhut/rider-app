package com.rideshare.driverservice.repository;

import com.rideshare.driverservice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
