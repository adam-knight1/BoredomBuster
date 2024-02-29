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
            @RequestParam(required = false) String zip,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state) {

        logger.info("Received getWeatherInfo request with parameters - zipCode: {}, city: {}, state: {}", zip, city, state);

        try {
            WeatherDTO weatherInfo;
            if (zip != null && !zip.trim().isEmpty()) {
                logger.info("Querying weather information by zipCode: {}", zip);
                weatherInfo = weatherService.getWeatherFromAPI("zip", zip, null);
            } else if (city != null && !city.trim().isEmpty() && state != null && !state.trim().isEmpty()) {
                logger.info("Querying weather information by city: {} and state: {}", city, state);
                weatherInfo = weatherService.getWeatherFromAPI("city", city, state);
            }  else if (city != null && !city.trim().isEmpty()) {
                    logger.info("Querying weather information by city: {} without state", city);
                    weatherInfo = weatherService.getWeatherFromAPI("city", city, null);
            }   else {
                    logger.warn("No valid query parameters provided for weather information request.");
                    return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok(weatherInfo);
        } catch (Exception e) {
            logger.error("Error while retrieving weather information", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
