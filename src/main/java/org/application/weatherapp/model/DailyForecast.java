package org.application.weatherapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DailyForecast {
    private List<String> time;
    private List<Integer> weather_code;
    private List<Double> temperature_2m_max;
    private List<Double> temperature_2m_min;
    private List<Double> sunshine_duration;
}
