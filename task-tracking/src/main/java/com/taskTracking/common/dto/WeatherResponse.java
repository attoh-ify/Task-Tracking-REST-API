package com.taskTracking.common.dto;

public class WeatherResponse {
    private String city;
    private String description;
    private double temperature;

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
