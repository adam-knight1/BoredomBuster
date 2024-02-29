package org.informationblitz.controller;

import org.informationblitz.dto.DogDTO;
import org.informationblitz.dto.WeatherDTO;
import org.informationblitz.service.DogService;
import org.informationblitz.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<WeatherDTO> getWeatherInfo(@RequestParam String zipCode) throws IOException { //string or int?
        try {
            WeatherDTO weatherInfo = weatherService.getWeatherFromAPI();
            return ResponseEntity.ok(weatherInfo);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
