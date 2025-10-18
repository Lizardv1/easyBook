package org.easybook.service.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Hotel {
    private Long id;
    private String name;
    private String description;

    private List<Room> rooms;
}
