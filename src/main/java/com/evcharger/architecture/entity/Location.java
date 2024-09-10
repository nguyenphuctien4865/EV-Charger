package com.evcharger.architecture.entity;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String streetAddress;

    @Column(length = 100, nullable = false)
    private String district;

    @Column(length = 100, nullable = false)
    private String cityProvince;

    @Column(length = 100, nullable = false)
    private String country;

    @Column(length = 20)
    private String postalCode;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Valid
    @ElementCollection
    private List<OperatingHours> operatingHours;

    @Column(length = 100)
    private String pricing;

    @Column(length = 50)
    private String phoneNumber;

    @Column(length = 50)
    private String parkingLevel;

    @OneToOne(mappedBy = "location")
    private EVCharger evCharger;

}
