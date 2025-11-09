package org.easybook.booking.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;

import java.time.LocalDate;

public record HotelsRequest(
        @JsonbDateFormat("yyyy-M-d")
        LocalDate checkIn,
        @JsonbDateFormat("yyyy-M-d")
        LocalDate checkOut,
        String countryCode,
        String city,
        HotelFilterRequest hotelFilter,
        RoomFilterRequest roomFilter,
        Integer page,
        Integer pageSize
) {
        private static final int DEFAULT_PAGE_SIZE = 25;

        public Integer getLimit() {
                return pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
        }

        public Integer getOffset() {
                return page == null ? 0 : page * getLimit();
        }
}
