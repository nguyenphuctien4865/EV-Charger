package com.evcharger.architecture.entity.documents;

import java.time.LocalTime;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatingHoursDocument {

    @Field(type = FieldType.Keyword)
    private String dayOfWeek;  // DayOfWeek is stored as a String in Elasticsearch

    @Field(type = FieldType.Keyword)
    private LocalTime openTime;

    @Field(type = FieldType.Keyword)
    private LocalTime closeTime;

    // Utility method to check if a specific time is within open hours
    public boolean isOpenAt(LocalTime time) {
        return openTime != null && closeTime != null && time.isAfter(openTime) && time.isBefore(closeTime);
    }

    // Check if the business is closed all day
    public boolean isClosed() {
        return openTime == null && closeTime == null;
    }
}
