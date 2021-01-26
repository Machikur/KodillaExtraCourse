package com.kodilla.weather;

public class LazyWeatherInformer implements WeatherInformer {

    private String weather="sunny";

    @Override
    public String getWeather() {
        return weather;
    }

    @Override
    public void refreshData() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("aktualizuje pogodę");
        weather = "rainy";
    }

}
