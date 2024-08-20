package com.yilmazgokhan.architecture.model.powerPlugType;

import lombok.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PowerPlugTypeModel {

    private Long id;

    private String powerModel;

    @NotBlank
    private String plugType;

    private String plugImage;  // Store image URL or path

    @Size(max = 300)
    private String usedInRegions;

    @Size(max = 1000)
    private String additionalNotes;
}
