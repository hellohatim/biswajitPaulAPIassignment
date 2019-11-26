package com.org.home;

import com.org.home.utility.WeatherAppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(WeatherAppProperties.class)
public class WeatherApp {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApp.class, args);
    }
}