package org.easybook.booking.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;

import java.time.LocalDate;

public record HotelsRequest(
        @JsonbDateFormat("yyyy-M-d")
        LocalDate checkIn,
        @JsonbDateFormat("yyyy-M-d")
        LocalDate checkOut
) {}
