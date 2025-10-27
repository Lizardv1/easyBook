package org.easybook.booking.mappers;

import com.querydsl.core.Tuple;
import jakarta.enterprise.context.ApplicationScoped;
import org.easybook.booking.domain.Hotel;
import org.easybook.booking.domain.QHotels;

@ApplicationScoped
public class HotelMapper {

    public Hotel map(QHotels hotel, Tuple tuple) {
        if (tuple == null) {
            return null;
        }

        return Hotel.builder()
                .id(tuple.get(hotel.id))
                .name(tuple.get(hotel.name))
                .phone(tuple.get(hotel.phone))
                .email(tuple.get(hotel.email))
                .description(tuple.get(hotel.description))
                .createdAt(tuple.get(hotel.createdAt).toLocalDateTime())
                .updatedAt(tuple.get(hotel.updatedAt).toLocalDateTime())
                .build();
    }

}
