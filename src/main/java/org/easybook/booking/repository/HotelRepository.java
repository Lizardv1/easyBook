package org.easybook.booking.repository;

import com.querydsl.core.Tuple;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.easybook.booking.domain.*;
import org.easybook.booking.dto.HotelsRequest;
import org.easybook.booking.mappers.HotelMapper;
import org.easybook.booking.repository.filters.HotelFilter;

import java.util.List;

@ApplicationScoped
public class HotelRepository {
    private final SQLQueryFactory queryFactory;
    private final HotelMapper hotelMapper;
    private final HotelFilter hotelFilter;

    private final QHotels h = QHotels.hotels;
    private final QAddresses a = QAddresses.addresses;
    private final QRooms r = QRooms.rooms;
    private final QBookings b = QBookings.bookings;
    private final QHotelToOption hto = QHotelToOption.hotelToOption;
    private final QHotelOptions ho = QHotelOptions.hotelOptions;
    private final QRoomOptions ro = QRoomOptions.roomOptions;
    private final QRoomToOption rto = QRoomToOption.roomToOption;

    @Inject
    public HotelRepository(SQLQueryFactory queryFactory, HotelMapper hotelMapper, HotelFilter hotelFilter) {
        this.queryFactory = queryFactory;
        this.hotelMapper = hotelMapper;
        this.hotelFilter = hotelFilter;
    }

    public List<Hotel> findAll(HotelsRequest request) {
        SQLQuery<Tuple> query = queryFactory.select(
                        h.id,
                        h.name,
                        h.description,
                        h.phone,
                        h.email,
                        ho.id,
                        ho.name
                ).distinct()
                .from(h)
                .leftJoin(a).on(a.id.eq(h.addressId))
                .leftJoin(r).on(r.hotelId.eq(h.id))
                .leftJoin(hto).on(hto.hotelId.eq(h.id))
                .leftJoin(ho).on(ho.id.eq(hto.optionId))
                .leftJoin(rto).on(rto.roomId.eq(r.id))
                .leftJoin(ro).on(ro.id.eq(rto.optionId))
                .leftJoin(b).on(b.roomId.eq(r.id)
                        .and(b.checkInDate.lt(request.checkOut()))
                        .and(b.checkOutDate.gt(request.checkIn())))
                .where(hotelFilter.getHotelPredicates(request))
                .limit(request.getLimit())
                .offset(request.getOffset());

        return hotelMapper.map(h, query.fetch());
    }
}
