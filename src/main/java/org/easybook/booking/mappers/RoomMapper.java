package org.easybook.booking.mappers;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import jakarta.enterprise.context.ApplicationScoped;
import org.easybook.booking.domain.QRoomOptions;
import org.easybook.booking.domain.QRooms;
import org.easybook.booking.domain.Room;

import java.util.*;

@ApplicationScoped
public class RoomMapper {

    public Room map(QRooms qRooms, QRoomOptions qOptions, Tuple tuple) {
        if (tuple == null) {
            return null;
        }
        List<String> options = Optional.ofNullable(tuple.get(
                        Expressions.template(String[].class, "ARRAY_AGG(DISTINCT {0})", qOptions.name)))
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
        return Room.builder()
                .id(tuple.get(qRooms.id))
                .number(tuple.get(qRooms.number))
                .capacity(tuple.get(qRooms.capacity))
                .description(tuple.get(qRooms.description))
                .options(options)
                .build();
    }

    public List<Room> map(QRooms qRooms, QRoomOptions qOptions, List<Tuple> tuples) {
        if (tuples == null || tuples.isEmpty()) {
            return Collections.emptyList();
        }


        return tuples.stream()
                .map(tuple -> map(qRooms, qOptions, tuple))
                .toList();
    }

}
