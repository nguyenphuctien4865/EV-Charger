package com.evcharger.architecture.entity.documents;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerPlugTypeDocument {

    @Field(type = FieldType.Keyword)
    private String powerModel;

    @Field(type = FieldType.Keyword)
    private String plugType;

    @Field(type = FieldType.Keyword)
    private String plugImage; // Store image URL or path

    @Field(type = FieldType.Text)
    private String usedInRegions;

    @Field(type = FieldType.Text)
    private String additionalNotes;
}