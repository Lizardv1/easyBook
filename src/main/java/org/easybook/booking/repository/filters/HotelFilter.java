package org.easybook.booking.repository.filters;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import io.micrometer.common.util.StringUtils;
import jakarta.enterprise.context.ApplicationScoped;
import org.easybook.booking.domain.*;
import org.easybook.booking.dto.HotelFilterRequest;
import org.easybook.booking.dto.HotelsRequest;
import org.easybook.booking.dto.RoomFilterRequest;

import java.util.Optional;

@ApplicationScoped
public class HotelFilter {

    public Predicate getHotelPredicates(HotelsRequest request) {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(addressPredicate(buildAddress(request)));
        addHotelFilters(request.hotelFilter(), predicate);
        addRoomFilters(request.roomFilter(), predicate);
        return predicate;
    }

    void addHotelFilters(HotelFilterRequest hotelFilter, BooleanBuilder predicate) {
        Optional.ofNullable(hotelFilter)
                .ifPresent(filter -> {
                    if (filter.options() != null && !filter.options().isEmpty())
                        predicate.and(QHotelOptions.hotelOptions.id.in(hotelFilter.options()));
                });
    }

    void addRoomFilters(RoomFilterRequest roomFilter, BooleanBuilder predicate) {
        Optional.ofNullable(roomFilter)
                .ifPresent(filter -> {
                    if (!StringUtils.isBlank(filter.type()))
                        predicate.and(QRooms.rooms.type.eq(filter.type()));
                    if (filter.options() != null && !filter.options().isEmpty())
                        QRoomOptions.roomOptions.id.in(roomFilter.options());
                });
    }

    BooleanExpression addressPredicate(Address address) {
        QAddresses qAddress = QAddresses.addresses;
        return qAddress.country.eq(address.getCountry())
                .and(qAddress.city.eq(address.getCity()));
    }

    private Address buildAddress(HotelsRequest request) {
        return Address.builder()
                .country(request.countryCode())
                .city(request.city())
                .build();
    }
}
