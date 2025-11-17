package org.easybook.booking.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Address {
    private Long id;
    private String country;
    private String city;
    private String street;
    private String building;
    private Integer zip;
}
