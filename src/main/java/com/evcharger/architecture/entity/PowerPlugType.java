package com.evcharger.architecture.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private String plugImage; // Store image URL or path

    @Column(name = "used_in_regions")
    private String usedInRegions;

    @Column(name = "additional_notes")
    private String additionalNotes;
}
