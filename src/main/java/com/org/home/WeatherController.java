package com.org.home;

import com.org.home.model.WeatherForecastVo;
import com.org.home.validation.UsZipCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/weather")
@Validated
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/tommorrow/{country}/{zipcode}")
    public WeatherForecastVo getWeatherForecast(@PathVariable(required = true) String country,
                                                @Valid @PathVariable(required = true) @UsZipCode String zipcode) {
        return this.weatherService.getWeatherForecast(country, zipcode);
    }
}
