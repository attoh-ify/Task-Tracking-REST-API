package com.taskTracking.weather;

import com.taskTracking.common.dto.WeatherResponse;
import com.taskTracking.common.exceptions.AppException;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
public class WeatherService {
    private static final String apiKey = System.getProperty("api.key");
    private static final String baseUrl = System.getProperty("base.url");

    private final Client client = ClientBuilder.newClient();

    public WeatherResponse getWeather(String city) {
        String url = String.format(baseUrl, city, apiKey);

        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() != 200) {
            throw new AppException("Failed to fetch weather data", 400);
        }

        var json = response.readEntity(javax.json.JsonObject.class);

        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setCity(city);
        weatherResponse.setDescription(json.getJsonArray("weather").getJsonObject(0).getString("description"));
        weatherResponse.setTemperature(json.getJsonObject("main").getJsonNumber("temp").doubleValue());
        System.out.println(response);
        return weatherResponse;
    }
}
