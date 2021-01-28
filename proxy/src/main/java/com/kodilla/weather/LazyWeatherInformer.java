package com.kodilla.weather;

public class LazyWeatherInformer implements WeatherInformer {

    private StandardWeatherInformer informer;

    @Override
    public String getWeather() throws InterruptedException {
        initializeInformer();
        return informer.getWeather();
    }

    @Override
    public void refreshData() throws InterruptedException {
        initializeInformer();
        informer.refreshData();
    }

    private void initializeInformer() throws InterruptedException {
        if (informer == null) {
            informer = new StandardWeatherInformer();
        }
    }

}
