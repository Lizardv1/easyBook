package org.easybook.service.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Room {
    private Long id;
    private String name;
}
