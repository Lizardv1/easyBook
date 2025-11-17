package org.easybook.booking.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.easybook.booking.domain.Hotel;
import org.easybook.booking.dto.HotelsRequest;
import org.easybook.booking.dto.PageResponse;
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllHotels(
            @Valid HotelsRequest request,
            @QueryParam("page") @DefaultValue("1") Integer page,
            @QueryParam("size") @DefaultValue("25") Integer size
    ) {
        PageResponse<List<Hotel>> pageresponse = hotelService.findAll(request, page, size);
        return Response.ok(pageresponse).build();
    }

    @POST
    @Path("/{hotelId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel getById(@PathParam("hotelId") Integer hotelId, HotelsRequest request) {
        return hotelService.findBy(hotelId, request);
    }
}
