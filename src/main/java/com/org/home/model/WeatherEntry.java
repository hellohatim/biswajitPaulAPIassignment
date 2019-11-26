package com.org.home.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.beans.Transient;
import java.time.Instant;
import java.util.List;
import java.util.Map;
@JsonPropertyOrder({"weatherId","date","time","temperature","temp_min","temp_max","humidity","weatherId"})
public class WeatherEntry {
    private Instant timestamp;

    private double temperature;

    private Integer weatherId;

    private String weatherIcon;
    private String time;
    private String date;

    private double temp_min;
    private double temp_max;
    private double humidity;


    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dt_txt) {
        this.date = dt_txt.split(" ")[0];
        ;
    }

    @JsonGetter("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("dt_txt")
    public void setTime(String dt_txt) {
        this.time = dt_txt.split(" ")[1];
        this.date = dt_txt.split(" ")[0];
    }

    @JsonProperty("timestamp")
    public Instant getTimestamp() {
        return this.timestamp;
    }

    @JsonSetter("dt")
    public void setTimestamp(long unixTime) {
        this.timestamp = Instant.ofEpochMilli(unixTime * 1000);
    }

    /**
     * Return the temperature in Kelvin (K).
     */
    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("main")
    public void setMain(Map<String, Object> main) {
        setTemperature(Double.parseDouble(main.get("temp").toString()));
        setTemp_min(Double.parseDouble(main.get("temp_min").toString()));
        setTemp_max(Double.parseDouble(main.get("temp_max").toString()));
        setHumidity(Double.parseDouble(main.get("humidity").toString()));
    }

    public Integer getWeatherId() {
        return this.weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }

    public String getWeatherIcon() {
        return this.weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        setWeatherId((Integer) weather.get("id"));
        setWeatherIcon((String) weather.get("icon"));
    }
}
