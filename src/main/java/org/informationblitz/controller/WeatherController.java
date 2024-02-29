package org.informationblitz.controller;

import org.informationblitz.dto.DogDTO;
import org.informationblitz.dto.WeatherDTO;
import org.informationblitz.service.DogService;
import org.informationblitz.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    public WeatherController (WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast")
    public ResponseEntity<WeatherDTO> getWeatherInfo(
            @RequestParam(required = false) String zipCode,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon) {
        try {
            WeatherDTO weatherInfo = null;

            if (zipCode != null && !zipCode.isEmpty()) {
                weatherInfo = weatherService.getWeatherFromAPI("zip", zipCode, null, null);
            } else if (city != null && !city.isEmpty()) {
                weatherInfo = weatherService.getWeatherFromAPI("city", city, state, country);
            } else if (lat != null && lon != null) {
                String latLon = lat + "," + lon;
                weatherInfo = weatherService.getWeatherFromAPI("latlon", latLon, null, null);
            } else {
                return ResponseEntity.badRequest().body(null); // No valid query parameters provided
            }

            if (weatherInfo != null) {
                return ResponseEntity.ok(weatherInfo);
            } else {
                return ResponseEntity.notFound().build(); // Weather information not found for the given parameters
            }
        } catch (Exception e) {
            e.printStackTrace(); // optional logger addition
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
