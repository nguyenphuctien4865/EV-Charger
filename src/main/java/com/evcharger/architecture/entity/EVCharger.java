package com.evcharger.architecture.entity;

import java.time.LocalDate;

import javax.persistence.*;

import com.evcharger.architecture.util.enums.Availability;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ev_chargers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EVCharger {

    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;
    @Id
    @Column(unique = true)
    private String chargerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "power_output_id", nullable = false)
    private PowerOutput powerOutput;

    @ManyToOne
    @JoinColumn(name = "power_plug_type_id", nullable = false)
    private PowerPlugType powerPlugType;

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
