package org.easybook.booking.mappers;

import com.querydsl.core.Tuple;
import jakarta.enterprise.context.ApplicationScoped;
import org.easybook.booking.domain.Hotel;
import org.easybook.booking.domain.OptionPair;
import org.easybook.booking.domain.QHotelOptions;
import org.easybook.booking.domain.QHotels;

import java.util.*;

@ApplicationScoped
public class HotelMapper {
    private final QHotelOptions hotelOptions = QHotelOptions.hotelOptions;
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
                .options(new ArrayList<>())
                .build();
    }

    public List<Hotel> map(QHotels qHotel, List<Tuple> tuples) {
        Map<Long, Hotel> hotelMap = new HashMap<>();
        tuples.forEach(tuple -> {
                    Hotel hotel = hotelMap.computeIfAbsent(
                            tuple.get(qHotel.id),
                            id -> map(qHotel, tuple)
                    );
                    Optional.ofNullable(tuple.get(hotelOptions.id))
                            .ifPresent(id -> hotel.getOptions().add(new OptionPair(
                                    id,
                                    tuple.get(hotelOptions.name)
                            )));
                });

        return new ArrayList<>(hotelMap.values());
    }
}
