package org.application.weatherapp.controller;

import java.util.*;

import org.application.weatherapp.model.WeatherData;
import org.application.weatherapp.model.WeatherForecast;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {
    @GetMapping("/test")
    public String test(){
        return "Application is up";
    }

    @GetMapping("/weather/forecast/{latitude}/{longitude}")
    public List<WeatherForecast> getSevenDayWeatherForecast(@PathVariable("latitude") double latitude,
                                                            @PathVariable("longitude") double longitude){
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration";
        RestTemplate restTemplate = new RestTemplate();
        List<WeatherForecast> weatherForecasts = new ArrayList<>();
        WeatherData weatherData = restTemplate.getForObject(apiUrl, WeatherData.class);

        if (weatherData == null || weatherData.getDaily() == null) {
            // Handle the case where data is not available
            return null;
        }

        List<String> dates = weatherData.getDaily().getTime();
        List<Integer> weatherCodes = weatherData.getDaily().getWeather_code();
        List<Double> temperaturesMax = weatherData.getDaily().getTemperature_2m_max();
        List<Double> temperaturesMin = weatherData.getDaily().getTemperature_2m_min();
        List<Double> sunshineDurations = weatherData.getDaily().getSunshine_duration();

        double solarPanelPower = 2.5; // kW
        double panelEfficiency = 0.2;

        for(int i = 0; i < dates.size(); i++){
            double exposureTime = sunshineDurations.get(i);
            double estimatedEnergy = solarPanelPower * exposureTime * panelEfficiency;
            WeatherForecast weatherForecast = new WeatherForecast();
            weatherForecast.setDate(dates.get(i));
            weatherForecast.setWeatherCode(weatherCodes.get(i));
            weatherForecast.setMinTemperature(temperaturesMin.get(i));
            weatherForecast.setMaxTemperature(temperaturesMax.get(i));
            weatherForecast.setEstimatedEnergy(estimatedEnergy);
            weatherForecasts.add(weatherForecast);
        }
        return weatherForecasts;
    }

}
