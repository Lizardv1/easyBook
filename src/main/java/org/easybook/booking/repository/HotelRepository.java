package org.easybook.booking.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.easybook.booking.domain.*;
import org.easybook.booking.dto.HotelsRequest;
import org.easybook.booking.dto.PageResponse;
import org.easybook.booking.mappers.HotelMapper;
import org.easybook.booking.repository.predicates.PredicatesFactory;

import java.util.Collections;
import java.util.List;

import static org.easybook.booking.utils.PageUtils.countPage;
import static org.easybook.booking.utils.PageUtils.countTotalPages;

@ApplicationScoped
public class HotelRepository {
    private final SQLQueryFactory queryFactory;
    private final HotelMapper hotelMapper;
    private final RoomRepository roomRepository;
    private final PredicatesFactory predicatesFactory = new PredicatesFactory();

    private final QHotels h = QHotels.hotels;
    private final QAddresses a = QAddresses.addresses;
    private final QRooms r = QRooms.rooms;
    private final QBookings b = QBookings.bookings;
    private final QHotelOptions ho = QHotelOptions.hotelOptions;

    @Inject
    public HotelRepository(SQLQueryFactory queryFactory, HotelMapper hotelMapper, RoomRepository roomRepository) {
        this.queryFactory = queryFactory;
        this.hotelMapper = hotelMapper;
        this.roomRepository = roomRepository;
    }

    public PageResponse<List<Hotel>> findAll(HotelsRequest request, Integer limit, Integer offset) {
        SQLQuery<Tuple> query = findAllQuery(request, limit, offset);
        int countTotal = countForFindAll(request);
        List<Hotel> hotels = countTotal == 0
                ? Collections.emptyList()
                : query.fetch().stream()
                .map(tpl -> hotelMapper.map(h, a, tpl))
                .toList();

        return new PageResponse<>(
                countPage(limit, offset),
                countTotalPages(countTotal, limit),
                hotels);
    }

    public Hotel findBy(Integer hotelId, HotelsRequest request) {
        SQLQuery<Tuple> query = findByIdQuery(hotelId);
        Tuple tuple = query.fetchOne();
        Hotel hotel = hotelMapper.map(h, a, tuple);
        if (hotel != null) {
            List<Room> rooms = roomRepository.findBy(hotelId, request);
            if (rooms.isEmpty())
                throw new IllegalStateException("Inconsistent data. A hotel rooms list can't be empty.");
            hotel.setRooms(rooms);
        }
        return hotel;
    }

    private SQLQuery<Tuple> findAllQuery(HotelsRequest request, Integer limit, Integer offset) {
        return queryFactory.select(
                        h.id,
                        h.name,
                        h.description,
                        h.phone,
                        h.email,
                        Expressions.template(String[].class, "ARRAY_AGG(DISTINCT {0})", ho.name),
                        a.country,
                        a.city,
                        a.street,
                        a.building)
                .from(h)
                .join(a).on(h.addressId.eq(a.id))
                .join(r).on(h.id.eq(r.hotelId))
                .leftJoin(b).on(b.roomId.eq(r.id)
                        .and(b.checkInDate.lt(request.checkOut()))
                        .and(b.checkOutDate.gt(request.checkIn())))
                .leftJoin(ho).on(Expressions.booleanTemplate(
                        "{0} = ANY({1})",
                        ho.id, h.options))
                .where(predicatesFactory.buildPredicate(request))
                .groupBy(h.id, h.name, h.description, h.phone, h.email, a.country, a.city, a.street, a.building)
                .limit(limit)
                .offset(offset);
    }

    private Integer countForFindAll(HotelsRequest request) {
        SQLQuery<Long> query = queryFactory.select(h.id.countDistinct())
                .from(h)
                .join(a).on(h.addressId.eq(a.id))
                .join(r).on(h.id.eq(r.hotelId))
                .leftJoin(b).on(b.roomId.eq(r.id)
                        .and(b.checkInDate.lt(request.checkOut()))
                        .and(b.checkOutDate.gt(request.checkIn())))
                .leftJoin(ho).on(Expressions.booleanTemplate(
                        "{0} = ANY({1})",
                        ho.id, h.options))
                .where(predicatesFactory.buildPredicate(request));

        return Long.valueOf(query.fetchCount()).intValue();
    }

    private SQLQuery<Tuple> findByIdQuery(Integer hotelId) {
        return queryFactory.select(
                        h.id,
                        h.name,
                        h.description,
                        h.phone,
                        h.email,
                        Expressions.template(String[].class, "ARRAY_AGG(DISTINCT {0})", ho.name),
                        a.country,
                        a.city,
                        a.street,
                        a.building)
                .from(h)
                .join(a).on(h.addressId.eq(a.id))
                .leftJoin(ho).on(Expressions.booleanTemplate(
                        "{0} = ANY({1})",
                        ho.id, h.options))
                .where(h.id.eq(hotelId))
                .groupBy(h.id, h.name, h.description, h.phone, h.email, a.country, a.city, a.street, a.building);
    }
}
