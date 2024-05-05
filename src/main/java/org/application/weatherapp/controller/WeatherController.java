package org.application.weatherapp.controller;

import java.util.*;


import org.application.weatherapp.model.WeatherData;
import org.application.weatherapp.model.WeatherForecast;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class WeatherController {

    private final RestTemplate restTemplate;

    public WeatherController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @GetMapping("/test")
    public String test(){
        return "Application is up";
    }

    @GetMapping("/weather/forecast/{latitude}/{longitude}")
    public List<WeatherForecast> getSevenDayWeatherForecast(@PathVariable("latitude") Double latitude,
                                                            @PathVariable("longitude") Double longitude){

        if (latitude == null || longitude == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Latitude and longitude are required");
        }

        if(latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid latitude or longitude");
        }

        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                "&longitude=" + longitude +
                "&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration";

        List<WeatherForecast> weatherForecasts = new ArrayList<>();
        WeatherData weatherData;

        try {
            weatherData = restTemplate.getForObject(apiUrl, WeatherData.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while fetching weather data from external API");
        }

        if (weatherData == null || weatherData.getDaily() == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No weather data available");
        }

        List<String> dates = weatherData.getDaily().getTime();
        List<Integer> weatherCodes = weatherData.getDaily().getWeather_code();
        List<Double> temperaturesMax = weatherData.getDaily().getTemperature_2m_max();
        List<Double> temperaturesMin = weatherData.getDaily().getTemperature_2m_min();
        List<Double> sunshineDurations = weatherData.getDaily().getSunshine_duration();

        double solarPanelPower = 2.5; // kW
        double panelEfficiency = 0.2;

        if (dates != null) {
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
        }
        return weatherForecasts;
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(ResponseStatusException exception){
        return new ResponseEntity<>(exception.getReason(), exception.getStatusCode());
    }

}
