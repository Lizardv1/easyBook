package org.easybook.booking.repository.predicates;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.Expressions;
import org.easybook.booking.domain.*;
import org.easybook.booking.dto.HotelsRequest;

public class PredicatesFactory {
    private final QHotels h = QHotels.hotels;
    private final QAddresses a = QAddresses.addresses;
    private final QRooms r = QRooms.rooms;
    private final QBookings b = QBookings.bookings;

    public BooleanBuilder buildPredicate(HotelsRequest request) {
        BooleanBuilder builder = new BooleanBuilder(b.id.isNull());
        builder.and(a.country.eq(request.countryCode()));
        builder.and(a.city.eq(request.city()));
        builder.and(hotelOptionsFilter(request));
        builder.and(roomFilters(request));

        return builder;
    }

    public BooleanBuilder roomFilters(HotelsRequest request) {
        BooleanBuilder builder = new BooleanBuilder();
        if (request.roomFilter().type() != null) {
            builder.and(r.type.eq(request.roomFilter().type()));
        }

        if (request.roomFilter().capacity() != null) {
            builder.and(r.capacity.eq(request.roomFilter().capacity()));
        }

        if (request.roomFilter().options() != null && !request.roomFilter().options().isEmpty()) {
            builder.and(ExpressionUtils.predicateTemplate(
                    "{0} @> {1}",
                    r.options,
                    Expressions.constant(request.roomFilter().options().toArray(new Integer[0]))
            ));
        }

        return builder;
    }

    public BooleanBuilder hotelOptionsFilter(HotelsRequest request) {
        BooleanBuilder builder = new BooleanBuilder();
        if (request.hotelFilter() != null
                && request.hotelFilter().options() != null
                && !request.hotelFilter().options().isEmpty()
        ) {
            builder.and(ExpressionUtils.predicateTemplate(
                    "{0} @> {1}",
                    h.options,
                    Expressions.constant(request.hotelFilter().options().toArray(new Integer[0]))
            ));
        }

        return builder;
    }
}
