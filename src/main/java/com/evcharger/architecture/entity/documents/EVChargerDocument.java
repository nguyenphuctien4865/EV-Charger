package com.evcharger.architecture.entity.documents;

import java.time.LocalDate;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;

import com.evcharger.architecture.util.enums.Availability;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "ev_chargers")
public class EVChargerDocument {

    @Id
    private String chargerId;

    @Field(type = FieldType.Nested)
    private LocationDocument location;

    @Field(type = FieldType.Nested)
    private PowerOutputDocument powerOutput;

    @Field(type = FieldType.Nested)
    private PowerPlugTypeDocument powerPlugType;

    @Field(type = FieldType.Integer)
    private Integer numberOfPorts;

    @Field(type = FieldType.Keyword)
    private Availability availability;

    @Field(type = FieldType.Date, format = DateFormat.basic_date)
    private LocalDate installationDate;

    @Field(type = FieldType.Date, format = DateFormat.basic_date)
    private LocalDate lastMaintenanceDate;
}