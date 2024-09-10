package com.evcharger.architecture.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PowerOutput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "output_value")
    private Double outputValue;

    @Column(name = "charging_speed")
    private String chargingSpeed;

    @Column(name = "voltage")
    private Integer voltage;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "powerOutput")
    private List<EVCharger> evChargers;

    @ManyToMany(mappedBy = "powerOutputs")
    private Set<PowerPlugType> powerPlugTypes;
}
