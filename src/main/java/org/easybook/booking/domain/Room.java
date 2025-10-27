package org.easybook.booking.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Room {
    private Long id;
    private Integer capacity;
    private String number;
    private String type;
    private String description;
}
