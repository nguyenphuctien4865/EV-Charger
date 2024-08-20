package com.yilmazgokhan.architecture.entity;

import com.yilmazgokhan.architecture.util.Constants;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
public class PowerPlugType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "power_model")
    private String powerModel;

    @Column(name = "plug_type")
    private String plugType;

    @Column(name = "plug_image")
    private String plugImage;  // Store image URL or path

    @Column(name = "used_in_regions")
    private String usedInRegions;

    @Column(name = "additional_notes")
    private String additionalNotes;
}
