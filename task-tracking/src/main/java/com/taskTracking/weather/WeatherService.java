package com.taskTracking.weather;

import com.taskTracking.common.dto.Weather;
import com.taskTracking.common.exceptions.AppException;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
public class WeatherService {
    private static final String apiKey = "dc1e645ca1045b1ca358e4b201173df7";
    private static final String baseUrl = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    private final Client client = ClientBuilder.newClient();

    public Weather getWeather(String city) {
        String url = String.format(baseUrl, city, apiKey);

        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() != 200) {
            throw new AppException("Failed to fetch weather data", 400);
        }

        var json = response.readEntity(javax.json.JsonObject.class);

        Weather weather = new Weather();
        weather.setCity(city);
        weather.setDescription(json.getJsonArray("weather").getJsonObject(0).getString("description"));
        weather.setTemperature(json.getJsonObject("main").getJsonNumber("temp").doubleValue());
        System.out.println(response);
        return weather;
    }
}
