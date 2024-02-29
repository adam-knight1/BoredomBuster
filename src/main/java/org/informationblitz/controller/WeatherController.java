package org.informationblitz.controller;

import org.informationblitz.dto.DogDTO;
import org.informationblitz.dto.WeatherDTO;
import org.informationblitz.service.DogService;
import org.informationblitz.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

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
            logger.info("Received getWeatherInfo request with parameters - zipCode: {}, city: {}, state: {}, country: {}, lat: {}, lon: {}", zipCode, city, state, country, lat, lon);


            if (zipCode != null && !zipCode.isEmpty()) {
                logger.info("Querying weather information by zipCode: {}", zipCode);
                weatherInfo = weatherService.getWeatherFromAPI("zip", zipCode, null, null);
            } else if (city != null && !city.isEmpty()) {
                logger.info("Querying weather information by city: {}, state: {}, country: {}", city, state, country);
                weatherInfo = weatherService.getWeatherFromAPI("city", city, state, country);
            } else if (lat != null && lon != null) {
                String latLon = lat + "," + lon;
                logger.info("Querying weather information by lat/lon: {}", latLon);
                weatherInfo = weatherService.getWeatherFromAPI("latlon", latLon, null, null);
            } else {
                logger.warn("No valid query parameters provided for weather information request.");
                return ResponseEntity.badRequest().body(null);
            }

            if (weatherInfo != null) {
                logger.info("Successfully retrieved weather information");
                return ResponseEntity.ok(weatherInfo);
            } else {
                logger.warn("Weather information not found for the given parameters.");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error while retrieving weather information", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
