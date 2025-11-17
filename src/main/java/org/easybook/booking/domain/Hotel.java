package org.easybook.booking.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class Hotel {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String description;
    private Address address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> options;
    private List<Room> rooms;
}
