package org.easybook.booking.repository;

import com.querydsl.sql.SQLQueryFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.easybook.booking.domain.Hotel;
import org.easybook.booking.domain.QBookings;
import org.easybook.booking.domain.QHotels;
import org.easybook.booking.domain.QRooms;
import org.easybook.booking.dto.HotelsRequest;
import org.easybook.booking.mappers.HotelMapper;

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

    public List<Hotel> findAll(HotelsRequest request) {
        QHotels h = QHotels.hotels;
        QRooms r = QRooms.rooms;
        QBookings b = QBookings.bookings;
        return queryFactory.select(
                        h.id,
                        h.name,
                        h.description,
                        h.phone,
                        h.email
                ).from(h)
                .leftJoin(r).on(r.hotelId.eq(h.id))
                .leftJoin(b).on(b.roomId.eq(r.id)
                        .and(b.checkInDate.lt(request.checkOut()))
                        .and(b.checkOutDate.gt(request.checkIn()))
                )
                .where(b.id.isNull())
                .fetch().stream()
                .map(tuple -> hotelMapper.map(h, tuple))
                .toList();
    }
}
