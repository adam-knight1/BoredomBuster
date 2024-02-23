package org.informationblitz.controller;

import org.informationblitz.service.DogService;

public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService){
        this.dogService = dogService;
    }

    @GetMapping
}
