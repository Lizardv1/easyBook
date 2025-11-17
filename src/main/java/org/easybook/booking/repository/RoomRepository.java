package org.easybook.booking.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.easybook.booking.domain.*;
import org.easybook.booking.dto.HotelsRequest;
import org.easybook.booking.mappers.RoomMapper;
import org.easybook.booking.repository.predicates.PredicatesFactory;

import java.util.List;

@ApplicationScoped
public class RoomRepository {
    private final SQLQueryFactory queryFactory;
    private final RoomMapper roomMapper;

    private final QRooms qRooms = QRooms.rooms;
    private final QRoomTypes qType = QRoomTypes.roomTypes;
    private final QBookings qBookings = QBookings.bookings;
    private final QRoomOptions qOptions = QRoomOptions.roomOptions;
    private final PredicatesFactory predicatesFactory = new PredicatesFactory();

    @Inject
    public RoomRepository(SQLQueryFactory queryFactory, RoomMapper roomMapper) {
        this.queryFactory = queryFactory;
        this.roomMapper = roomMapper;
    }

    List<Room> findBy(Integer hotelId, HotelsRequest request) {
        SQLQuery<Tuple> query = queryFactory.select(
                        qRooms.id,
                        qType.type,
                        qRooms.capacity,
                        qRooms.description,
                        Expressions.template(String[].class, "ARRAY_AGG(DISTINCT {0})", qOptions.name)
                )
                .from(qRooms)
                .leftJoin(qType).on(qType.id.eq(qRooms.type))
                .leftJoin(qOptions).on(Expressions.booleanTemplate("{0} = ANY({1})", qOptions.id, qRooms.options))
                .leftJoin(qBookings).on(qBookings.roomId.eq(qRooms.id)
                        .and(qBookings.checkInDate.lt(request.checkOut()))
                        .and(qBookings.checkOutDate.gt(request.checkIn())))
                .where(qRooms.hotelId.eq(hotelId)
                        .and(predicatesFactory.roomFilters(request)))
                .groupBy(qRooms.id,
                        qType.type,
                        qRooms.capacity,
                        qRooms.description);

        List<Tuple> resultSet = query.fetch();
        return roomMapper.map(qRooms, qOptions, resultSet);
    }
}
