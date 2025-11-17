package org.easybook.booking.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.easybook.booking.domain.Hotel;
import org.easybook.booking.dto.HotelsRequest;
import org.easybook.booking.dto.PageResponse;
import org.easybook.booking.repository.HotelRepository;
import org.easybook.booking.utils.PageUtils;

import java.util.List;

@ApplicationScoped
public class HotelService {

    private final HotelRepository hotelRepository;

    @Inject
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public PageResponse<List<Hotel>> findAll(HotelsRequest request, Integer page, Integer size) {
        int offset = PageUtils.countOffset(page, size);
        return hotelRepository.findAll(request, size, offset);
    }

    public Hotel findBy(Integer hotelId, HotelsRequest request) {
        return hotelRepository.findBy(hotelId, request);
    }
}
