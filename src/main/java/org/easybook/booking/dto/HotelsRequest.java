package org.easybook.booking.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record HotelsRequest(
        @JsonbDateFormat("yyyy-M-d")
        @NotNull(message = "checkIn is a mandatory field and can't be null or empty")
        LocalDate checkIn,
        @JsonbDateFormat("yyyy-M-d")
        @NotNull(message = "checkOut is a mandatory field and can't be null or empty")
        LocalDate checkOut,
        @NotEmpty(message = "countryCode is a mandatory field and can't be null or empty")
        String countryCode,
        @NotEmpty(message = "city is a mandatory field and can't be null or empty")
        String city,
        HotelFilterRequest hotelFilter,
        RoomFilterRequest roomFilter
) {}