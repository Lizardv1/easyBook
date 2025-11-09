package org.easybook.booking.dto;

import java.util.List;

public record RoomFilterRequest(
        String type,
        List<Integer> options
) {
}
