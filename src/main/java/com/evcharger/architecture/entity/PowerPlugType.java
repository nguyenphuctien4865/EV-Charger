package com.evcharger.architecture.entity;

import javax.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "powerPlugType")
    private List<EVCharger> evChargers;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "power_plug_type_power_output", joinColumns = @JoinColumn(name = "power_plug_type_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "power_output_id", referencedColumnName = "id"))
    private Set<PowerOutput> powerOutputs;
}
