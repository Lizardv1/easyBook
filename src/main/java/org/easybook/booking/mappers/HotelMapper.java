package org.easybook.booking.mappers;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import jakarta.enterprise.context.ApplicationScoped;
import org.easybook.booking.domain.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class HotelMapper {
    private final QHotelOptions ho = QHotelOptions.hotelOptions;

    public Hotel map(QHotels qHotel, QAddresses qAddresses, Tuple tuple) {
        if (tuple == null) {
            return null;
        }
        List<String> options = Optional.ofNullable(
                        tuple.get(Expressions.template(String[].class, "ARRAY_AGG(DISTINCT {0})", ho.name))
                )
                .map(Arrays::asList)
                .orElse(Collections.emptyList());

        return Hotel.builder()
                .id(tuple.get(qHotel.id))
                .name(tuple.get(qHotel.name))
                .email(tuple.get(qHotel.email))
                .phone(tuple.get(qHotel.phone))
                .description(tuple.get(qHotel.description))
                .options(options)
                .address(Address.builder()
                        .country(tuple.get(qAddresses.country))
                        .city(tuple.get(qAddresses.city))
                        .street(tuple.get(qAddresses.street))
                        .building(tuple.get(qAddresses.building))
                        .build())
                .build();
    }

}
