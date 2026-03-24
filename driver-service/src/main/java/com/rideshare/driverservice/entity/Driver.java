package com.rideshare.driverservice.entity;
import com.rideshare.driverservice.constant.DriverStatus; import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="drivers") @Getter @Setter public class Driver { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(unique=true) private String email; private String name; @Enumerated(EnumType.STRING) private DriverStatus status=DriverStatus.OFFLINE; }
