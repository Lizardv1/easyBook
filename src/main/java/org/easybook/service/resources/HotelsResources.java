package org.easybook.service.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.easybook.service.domain.Hotel;
import org.easybook.service.service.HotelService;

import java.util.List;

@Path("/hotels")
@ApplicationScoped
public class HotelsResources {

    @Inject
    private HotelService hotelService;

    @GET
    @Produces(value = "application/json")
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels("Parameter");
    }
}
