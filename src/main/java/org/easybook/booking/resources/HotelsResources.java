package org.easybook.booking.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.easybook.booking.domain.Hotel;
import org.easybook.booking.service.HotelService;

import java.util.List;

@Path("/hotels")
@ApplicationScoped
public class HotelsResources {

    private final HotelService hotelService;

    @Inject
    public HotelsResources(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GET
    @Produces(value = "application/json")
    public List<Hotel> getAllHotels() {
        return hotelService.findAll();
    }
}
