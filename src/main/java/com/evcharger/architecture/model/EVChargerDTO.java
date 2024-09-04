package com.evcharger.architecture.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.evcharger.architecture.util.validator.Availability;
import com.evcharger.architecture.util.validator.EnumPattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EVChargerDTO {

    @NotBlank(message = "Charger ID is mandatory")
    @Size(max = 50, message = "Charger ID must not exceed 50 characters")
    private String chargerId;

    @NotBlank(message = "Location ID is mandatory")
    private String locationId;

    @NotBlank(message = "Power Output ID is mandatory")
    private String powerOutputId;

    @NotBlank(message = "Power Plug Type ID is mandatory")
    private String powerPlugTypeId;

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
