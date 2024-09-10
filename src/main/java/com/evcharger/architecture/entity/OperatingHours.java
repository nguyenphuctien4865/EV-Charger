package com.evcharger.architecture.entity;

import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.persistence.Embeddable;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OperatingHours {

   @NotNull(message = "Day of week is required")
    private DayOfWeek dayOfWeek;

    @NotNull(message = "Opening time is required")
    private LocalTime openTime;

    @NotNull(message = "Closing time is required")
    private LocalTime closeTime;

    // Getters and setters

    @AssertTrue(message = "Open time must be before close time")
    public boolean isValidOperatingHours() {
        return (openTime != null && closeTime != null && openTime.isBefore(closeTime)) || (openTime == null && closeTime == null);
    }

    // Utility method to check if a specific time is within open hours
    public boolean isOpenAt(LocalTime time) {
        return openTime != null && closeTime != null && time.isAfter(openTime) && time.isBefore(closeTime);
    }

    // Check if the business is closed all day
    public boolean isClosed() {
        return openTime == null && closeTime == null;
    }
}