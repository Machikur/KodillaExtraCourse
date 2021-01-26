package com.kodilla.weather;

import java.util.Random;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        System.out.println("Zaczynam pętle ze StandardWeatherInformer");
        long before = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            System.out.printf("step %d start %n", i);
            WeatherInformer informer = new StandardWeatherInformer();
            int choose = random.nextInt(100);
            if (choose < 20) {
                informer.refreshData();
            }
            System.out.println(informer.getWeather());
            System.out.printf("step %d done %n%n", i);
        }
        System.out.printf("Czas wykonania StandardWeatherInformer %s[ms]%n%n", System.currentTimeMillis() - before);

        System.out.println("Zaczynam pętle ze LazyWeatherInformer");
        long beforeLazy = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            WeatherInformer informer = new LazyWeatherInformer();
            int choose = random.nextInt(100);
            if (choose < 20) {
                informer.refreshData();
            }
            System.out.println(informer.getWeather());
            System.out.printf("step %d done %n%n", i);
        }
        System.out.printf("Czas wykonania LazyWeatherInformer %s[ms]%n", System.currentTimeMillis() - beforeLazy);
    }

}
