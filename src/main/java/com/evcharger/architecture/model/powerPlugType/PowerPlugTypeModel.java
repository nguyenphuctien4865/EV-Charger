package com.evcharger.architecture.model.powerPlugType;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PowerPlugTypeModel {

    private Long id;

    @NotBlank(message = "Plug Type Model is mandatory.")
    @Pattern(regexp = "^(AC|DC)$", message = "Power Model must be either 'AC' or 'DC'.")
    private String powerModel;

    @NotBlank(message = "Plug Type is mandatory.")
    @Pattern(regexp = "^(Type 1|Type 2|CCS2|CHAdeMO|Tesla)$", message = "Plug Type must be one of the specified values.")
    private String plugType;
    
    private String plugImage; // Store image URL or path

    @Size(max = 300)
    private String usedInRegions;

    @Size(max = 1000)
    private String additionalNotes;
}
