package com.kodilla.weather;

public class StandardWeatherInformer implements WeatherInformer {

    private String weather;

    public StandardWeatherInformer() throws InterruptedException {
        refreshData();
    }

    @Override
    public String getWeather() {
        return weather;
    }

    @Override
    public void refreshData() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("aktualizuje pogodę");
        weather = "rainy";
    }
}
