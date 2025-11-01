package org.easybook.booking.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.easybook.booking.domain.Hotel;
import org.easybook.booking.dto.HotelsRequest;
import org.easybook.booking.service.HotelService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.List;

@Path("/hotels")
@ApplicationScoped
public class HotelsResources {

    private final HotelService hotelService;

    @Inject
    public HotelsResources(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @POST
    @Produces(value = "application/json")
    public List<Hotel> getAllHotels(@RequestBody HotelsRequest request) {
        return hotelService.findAll(request);
    }
}
