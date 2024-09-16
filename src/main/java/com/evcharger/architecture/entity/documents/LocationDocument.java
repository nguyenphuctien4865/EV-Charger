package com.evcharger.architecture.entity.documents;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDocument {

    @Field(type = FieldType.Text, analyzer = "standard")
    private String name;

    @Field(type = FieldType.Text)
    private String streetAddress;

    @Field(type = FieldType.Text)
    private String district;

    @Field(type = FieldType.Text)
    private String cityProvince;

    @Field(type = FieldType.Text)
    private String country;

    @Field(type = FieldType.Keyword)
    private String postalCode;

    @GeoPointField
    private GeoPoint location;

    @Field(type = FieldType.Nested)
    private List<OperatingHoursDocument> operatingHours;

    @Field(type = FieldType.Text)
    private String pricing;

    @Field(type = FieldType.Keyword)
    private String phoneNumber;

    @Field(type = FieldType.Keyword)
    private String parkingLevel;
}