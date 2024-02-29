package org.informationblitz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDTO {
    @JsonProperty("wind_speed")
    private double windSpeed;

    @JsonProperty("wind_degrees")
    private int windDegrees;

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("sunset")
    private long sunset;

    @JsonProperty("min_temp")
    private double minTemp;

    @JsonProperty("cloud_pct")
    private int cloudPct;

    @JsonProperty("feels_like")
    private double feelsLike;

    @JsonProperty("sunrise")
    private long sunrise;

    @JsonProperty("max_temp")
    private double maxTemp;

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDegrees() {
        return windDegrees;
    }

    public void setWindDegrees(int windDegrees) {
        this.windDegrees = windDegrees;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public int getCloudPct() {
        return cloudPct;
    }

    public void setCloudPct(int cloudPct) {
        this.cloudPct = cloudPct;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }
}
