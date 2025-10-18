package org.easybook.service.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.easybook.service.domain.Hotel;
import org.easybook.service.domain.Room;

import java.util.List;

@ApplicationScoped
public class HotelService {

    public List<Hotel> getAllHotels(String parameter) {
        return getHotels();
    }

    private List<Hotel> getHotels() {
        return List.of(
                Hotel.builder()
                        .id(1L)
                        .name("Hotel More")
                        .description("Hotel one description")
                        .rooms(List.of(
                                Room.builder()
                                        .id(1L)
                                        .name("Room name")
                                        .build()
                        ))
                        .build(),
                Hotel.builder()
                        .id(2L)
                        .name("Hotel Shachter")
                        .build(),
                Hotel.builder()
                        .id(3L)
                        .name("Hotel Paradise")
                        .build()
        );
    }

}
