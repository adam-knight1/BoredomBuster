package org.informationblitz.controller;

import org.informationblitz.service.DogService;
@Rest
@RequestMapping("/api")
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService){
        this.dogService = dogService;
    }

    @GetMapping("/dog")



    }
}
