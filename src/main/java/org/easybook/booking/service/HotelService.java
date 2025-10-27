package org.easybook.booking.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.easybook.booking.domain.Hotel;
import org.easybook.booking.repository.HotelRepository;

import java.util.List;

@ApplicationScoped
public class HotelService {

    private final HotelRepository hotelRepository;

    @Inject
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }
}
