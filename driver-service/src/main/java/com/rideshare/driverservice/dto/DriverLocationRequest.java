package com.rideshare.driverservice.dto;
import jakarta.validation.constraints.*; import lombok.Data;
@Data public class DriverLocationRequest { @NotNull private Long driverId; @DecimalMin("-90.0") @DecimalMax("90.0") private double lat; @DecimalMin("-180.0") @DecimalMax("180.0") private double lon; @NotNull private Boolean available; }
