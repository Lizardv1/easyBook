package org.easybook.booking.dto;

public record PageResponse<T>(
        Integer page,
        Integer total,
        T body
) {}
