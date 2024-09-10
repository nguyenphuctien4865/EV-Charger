package com.evcharger.architecture.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.evcharger.architecture.entity.OperatingHours;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Street address is mandatory")
    @Size(max = 255, message = "Street address must not exceed 255 characters")
    private String streetAddress;

    @NotBlank(message = "District is mandatory")
    @Size(max = 100, message = "District must not exceed 100 characters")
    private String district;

    @NotBlank(message = "City/Province is mandatory")
    @Size(max = 100, message = "City/Province must not exceed 100 characters")
    private String cityProvince;

    @NotBlank(message = "Country is mandatory")
    @Size(max = 100, message = "Country must not exceed 100 characters")
    private String country;

    @Size(max = 20, message = "Postal code must not exceed 20 characters")
    private String postalCode;

    @NotNull(message = "Latitude is mandatory")
    @DecimalMin(value = "-90.0", message = "Latitude must be greater than or equal to -90")
    @DecimalMax(value = "90.0", message = "Latitude must be less than or equal to 90")
    private Double latitude;

    @NotNull(message = "Longitude is mandatory")
    @DecimalMin(value = "-180.0", message = "Longitude must be greater than or equal to -180")
    @DecimalMax(value = "180.0", message = "Longitude must be less than or equal to 180")
    private Double longitude;

    @NotEmpty(message = "Operating hours are required")
    private List<@Valid OperatingHours> operatingHours;

    @Size(max = 100, message = "Pricing must not exceed 100 characters")
    private String pricing;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number format")
    private String phoneNumber;

    @Size(max = 50, message = "Parking level must not exceed 50 characters")
    private String parkingLevel;
}
