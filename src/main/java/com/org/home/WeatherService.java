package com.org.home;

import com.org.home.exception.ZipCodeNotFoundException;
import com.org.home.model.WeatherEntry;
import com.org.home.model.WeatherForecastVo;
import com.org.home.utility.WeatherAppProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private static final String FORECAST_URL =
            "http://api.openweathermap.org/data/2.5/forecast?q={zipcode},{country}&APPID={key}&units=metric";

    private final RestTemplate restTemplate;

    private final String apiKey;

    public WeatherService(RestTemplateBuilder restTemplateBuilder,
                          WeatherAppProperties properties) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiKey = properties.getApi().getKey();
    }

    public WeatherForecastVo getWeatherForecast(String country, String zipcode) {
        URI url = new UriTemplate(FORECAST_URL).expand(zipcode, country, this.apiKey);
        WeatherForecastVo weatherForecastVo = invoke(url, WeatherForecastVo.class);
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<WeatherEntry> entries = weatherForecastVo.getEntries().stream().filter(entry -> entry.getDate().equalsIgnoreCase(tomorrow.toString())).collect(Collectors.toList());
        weatherForecastVo.setEntries(entries);
        return weatherForecastVo;
    }

    private <T> T invoke(URI url, Class<T> responseType) {
        ResponseEntity<T> exchange = null;
        try {
            RequestEntity<?> request = RequestEntity.get(url)
                    .accept(MediaType.APPLICATION_JSON).build();
            exchange = this.restTemplate
                    .exchange(request, responseType);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode().is4xxClientError()) {
                throw new ZipCodeNotFoundException("Invalid Zip Code for US");
            } else {
                throw new ZipCodeNotFoundException("Error while connecting to " + FORECAST_URL);
            }
        }
        return exchange.getBody();
    }
}
