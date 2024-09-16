package com.evcharger.architecture.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.evcharger.architecture.util.enums.Availability;
import com.evcharger.architecture.util.validator.EnumPattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EVChargerDTO {

    // private String id;

    @NotBlank(message = "Charger ID is mandatory")
    @Size(max = 50, message = "Charger ID must not exceed 50 characters")
    private String chargerId;

    @NotNull(message = "Location is mandatory")
    private LocationDTO location;

    @NotNull(message = "Power Output is mandatory")
    private PowerOutputDTO powerOutput;

    @NotNull(message = "Power Plug Type is mandatory")
    private PowerPlugTypeDTO powerPlugType;

    @NotNull(message = "Number of Ports is mandatory")
    @Positive(message = "Number of Ports must be a positive integer")
    private Integer numberOfPorts;

    @NotNull(message = "Availability is mandatory")
    @EnumPattern(name = "availability", regexp = "AVAILABLE|IN_USE|OUT_OF_ORDER")
    private Availability availability;

    @PastOrPresent(message = "Installation Date cannot be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate installationDate;

    @PastOrPresent(message = "Last Maintenance Date cannot be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastMaintenanceDate;
}
