package com.evcharger.architecture.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.evcharger.architecture.util.enums.ChargingSpeed;
import com.evcharger.architecture.util.validator.EnumPattern;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PowerOutputDTO {

    private Long id;

    @NotNull(message = "Output value is mandatory")
    @Positive(message = "Output value must be a positive number")
    private Double outputValue;

    @NotNull(message = "Charging speed is mandatory")
    @EnumPattern(name = "chargingSpeed", regexp = "SLOW|FAST|ULTRA_FAST")
    private ChargingSpeed chargingSpeed;

    @NotNull(message = "Voltage is mandatory")
    @Positive(message = "Voltage value must be a positive number")
    private Integer voltage;

    @Size(max = 1000, message = "Description can have a maximum of 1000 characters")
    private String description;

    // Constructor without id for creating new power outputs
    public PowerOutputDTO(Double outputValue, ChargingSpeed chargingSpeed, Integer voltage, String description) {
        this.outputValue = outputValue;
        this.chargingSpeed = chargingSpeed;
        this.voltage = voltage;
        this.description = description;
    }
}
