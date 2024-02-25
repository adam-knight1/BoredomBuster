package org.informationblitz.controller;

import org.informationblitz.service.DogService;
import org.informationblitz.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    public WeatherController (WeatherService weatherService){
        this.weatherService = weatherService;
    }
}
