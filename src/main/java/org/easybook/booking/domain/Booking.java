package org.easybook.booking.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Booking {
    private Long id;
    private Room room;
    private Customer customer;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
