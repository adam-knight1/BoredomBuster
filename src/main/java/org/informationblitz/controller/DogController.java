package org.informationblitz.controller;

import org.informationblitz.dto.DogDTO;
import org.informationblitz.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/dogs")
public class DogController {

    @Autowired
    private DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/dog")
    public ResponseEntity<DogDTO> getDogInfo(@RequestParam String breedName) throws IOException {
        try {
            DogDTO dogInfo = dogService.getDogInfoFromAPI(breedName);
            return ResponseEntity.ok(dogInfo);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
