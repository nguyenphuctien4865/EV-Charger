package com.evcharger.architecture.entity.documents;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerOutputDocument {

    @Field(type = FieldType.Double)
    private Double outputValue;

    @Field(type = FieldType.Keyword)
    private String chargingSpeed;

    @Field(type = FieldType.Integer)
    private Integer voltage;

    @Field(type = FieldType.Text)
    private String description;
}