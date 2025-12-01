package com.taskTracking.weather;

import com.taskTracking.common.dto.WeatherResponse;
import com.taskTracking.common.response.ApiResponse;
import com.taskTracking.security.Secured;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/weather")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WeatherController {
    @Inject
    private WeatherService weatherService;

    @GET
    @Secured
    @Path("/{city}")
    public Response getWeather(@PathParam("city") String city) {
        WeatherResponse weatherResponse = weatherService.getWeather(city);
        return Response.ok(new ApiResponse<>(true, "Weather fetched successfully", weatherResponse)).build();
    }
}
