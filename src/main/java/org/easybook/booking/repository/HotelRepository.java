package org.easybook.booking.repository;

import com.querydsl.core.Tuple;
import com.querydsl.sql.SQLQueryFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.easybook.booking.domain.Hotel;
import org.easybook.booking.domain.QHotels;
import org.easybook.booking.mappers.HotelMapper;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class HotelRepository {
    private final SQLQueryFactory queryFactory;
    private final HotelMapper hotelMapper;

    @Inject
    public HotelRepository(SQLQueryFactory queryFactory, HotelMapper hotelMapper) {
        this.queryFactory = queryFactory;
        this.hotelMapper = hotelMapper;
    }

    public List<Hotel> findAll() {
        QHotels h = QHotels.hotels;
        List<Tuple> fetch = queryFactory.select(
                        h.id,
                        h.name,
                        h.description,
                        h.phone,
                        h.email,
                        h.createdAt,
                        h.updatedAt
                ).from(h)
                .fetch();

        if (fetch.isEmpty()) {
            return Collections.emptyList();
        }

        return fetch.stream()
                .map(tuple -> hotelMapper.map(h, tuple))
                .toList();
    }
}
