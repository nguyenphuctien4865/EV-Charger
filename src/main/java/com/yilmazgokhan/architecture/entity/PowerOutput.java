package com.yilmazgokhan.architecture.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
