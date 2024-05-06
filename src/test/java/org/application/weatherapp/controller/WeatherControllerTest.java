package org.application.weatherapp.controller;

import java.util.*;
import org.application.weatherapp.model.WeatherData;
import org.application.weatherapp.model.WeatherForecast;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WeatherControllerTest {
    @Test
    void testValidCoordinates_ReturnsForecast(){
        // Arrange
        double lat = 50.50;
        double lon = 13.405;


        RestTemplate restTemplate = mock(RestTemplate.class);
        WeatherController weatherController = new WeatherController(restTemplate);
        WeatherData weatherData = new WeatherData();

        List<String> dates = new ArrayList<>();
        dates.add("2024-05-05");
        weatherData.getDaily().setTime(dates);

        List<Integer> weatherCodes = new ArrayList<>();
        weatherCodes.add(800);
        weatherData.getDaily().setWeather_code(weatherCodes);

        List<Double> temperaturesMax = new ArrayList<>();
        temperaturesMax.add(23.5);
        weatherData.getDaily().setTemperature_2m_max(temperaturesMax);

        List<Double> temperaturesMin = new ArrayList<>();
        temperaturesMin.add(10.4);
        weatherData.getDaily().setTemperature_2m_min(temperaturesMin);

        List<Double> sunshineDurations = new ArrayList<>();
        sunshineDurations.add(80.0);
        weatherData.getDaily().setSunshine_duration(sunshineDurations);

        when(restTemplate.getForObject(anyString(), eq(WeatherData.class))).thenReturn(weatherData);

        // Act
        List<WeatherForecast> weatherForecasts = weatherController.getSevenDayWeatherForecast(lat, lon);

        // Assert
        assertEquals(1, weatherForecasts.size());
        assertEquals("2024-05-05", weatherForecasts.get(0).getDate());
        assertEquals(800, weatherForecasts.get(0).getWeatherCode());
        assertEquals(23.5, weatherForecasts.get(0).getMaxTemperature());
        assertEquals(10.4, weatherForecasts.get(0).getMinTemperature());
        assertEquals((double) Math.round(((80.0 * 2.5 * 0.2)/3600) * 100) / 100, weatherForecasts.get(0).getEstimatedEnergy());
    }

    @Test
    void testNullCoordinates_ThrowsException(){
        // Arrange
        Double lat = null;
        Double lon = null;

        RestTemplate restTemplate = mock(RestTemplate.class);
        WeatherController weatherController = new WeatherController(restTemplate);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () ->
        {weatherController.getSevenDayWeatherForecast(lat, lon);});
    }

    @Test
    void testInvalidCoordinates_ThrowsException(){
        // Arrange
        Double lat = 100.0;
        Double lon = -200.0;

        RestTemplate restTemplate = mock(RestTemplate.class);
        WeatherController weatherController = new WeatherController(restTemplate);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () ->
        {weatherController.getSevenDayWeatherForecast(lat, lon);});
    }

    @Test
    void testApiError_ThrowsException(){
        // Arrange
        Double lat = 50.50;
        Double lon = 13.405;

        RestTemplate restTemplate = mock(RestTemplate.class);
        WeatherController weatherController = new WeatherController(restTemplate);

        when(restTemplate.getForObject(anyString(), eq(WeatherData.class))).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () ->
        {weatherController.getSevenDayWeatherForecast(lat, lon);});
    }

    @Test
    void testNoDataAvailable_ThrowsException() {
        // Arrange
        Double lat = 50.50;
        Double lon = 13.405;

        RestTemplate restTemplate = mock(RestTemplate.class);
        WeatherController weatherController = new WeatherController(restTemplate);
        WeatherData weatherData = new WeatherData();

        when(restTemplate.getForObject(anyString(), eq(WeatherData.class))).thenReturn(weatherData);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> {
            weatherController.getSevenDayWeatherForecast(lat, lon);
        });
    }

}