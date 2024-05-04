package org.application.weatherapp.model;

import lombok.Getter;
import lombok.Setter;
import org.application.weatherapp.validation.ValidDateList;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DailyForecast {
    @NotEmpty
    @ValidDateList
    private List<String> time;

    @NotNull
    private List<Integer> weather_code;

    @NotNull
    private List<Double> temperature_2m_max;

    @NotNull
    private List<Double> temperature_2m_min;

    @NotNull
    private List<Double> sunshine_duration;
}
