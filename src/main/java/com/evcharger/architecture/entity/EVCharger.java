package com.evcharger.architecture.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.evcharger.architecture.util.validator.Availability;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chargers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EVCharger {

    @Id
    @Column(name = "charger_id", nullable = false, length = 50)
    private String chargerId;

    @Column(name = "location_id", nullable = false, length = 50)
    private String locationId;

    @Column(name = "power_output_id", nullable = false, length = 50)
    private String powerOutputId;

    @Column(name = "power_plug_type_id", nullable = false, length = 50)
    private String powerPlugTypeId;

    @Column(name = "number_of_ports", nullable = false)
    private Integer numberOfPorts;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability", nullable = false, length = 20)
    private Availability availability;

    @Column(name = "installation_date")
    private LocalDate installationDate;

    @Column(name = "last_maintenance_date")
    private LocalDate lastMaintenanceDate;

    // Getters and Setters
    // Constructors
}

