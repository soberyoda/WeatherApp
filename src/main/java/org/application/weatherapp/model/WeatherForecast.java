package org.application.weatherapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherForecast {
    private String date;
    private int weatherCode;
    private double minTemperature;
    private double maxTemperature;
    private double EstimatedEnergy;
}
