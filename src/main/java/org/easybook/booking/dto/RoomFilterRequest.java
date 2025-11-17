package org.easybook.booking.dto;

import java.util.List;

public record RoomFilterRequest(
        Integer type,
        Integer capacity,
        List<Integer> options
) {
}
