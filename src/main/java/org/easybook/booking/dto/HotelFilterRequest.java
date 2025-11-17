package org.easybook.booking.dto;

import java.util.List;

public record HotelFilterRequest(
        List<Integer> options
) {}
