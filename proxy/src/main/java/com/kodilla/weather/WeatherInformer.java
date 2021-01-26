package com.kodilla.weather;

public interface WeatherInformer {
    String getWeather() throws InterruptedException;

    void refreshData() throws InterruptedException;
}
