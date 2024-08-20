package com.yilmazgokhan.architecture.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PowerOutputModel {

    private Long id;


	@Positive(message = "Output value must be a positive number")
    private Double outputValue;
    
    @NotBlank(message = "Charging speed is mandatory")
    @Pattern(regexp = "Slow|Fast|Ultra-Fast", message = "Charging speed must be one of the following: Slow, Fast, Ultra-Fast")    
    private String chargingSpeed;
    
    @Positive(message = "Voltage value must be a positive number")
    private Integer voltage;

    @Size(max = 1000, message = "Description can have a maximum of 1000 characters")
    private String description;
}
