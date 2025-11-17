package org.easybook.booking.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Room {
    private Long id;
    private Integer capacity;
    private String number;
    private String type;
    private String description;
    private List<String> options;
}
