package org.application.weatherapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherData {
    private DailyForecast daily;

    public WeatherData(){
        this.daily = new DailyForecast();
    }
}
