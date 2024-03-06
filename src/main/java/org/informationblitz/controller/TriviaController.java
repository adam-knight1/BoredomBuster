package org.informationblitz.controller;

import org.informationblitz.dto.TriviaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trivia")
public class TriviaController {

    @GetMapping("/dogs")
    public ResponseEntity<TriviaDTO> getTriviaQuestion(@RequestParam String category){

        TriviaDTO trivia = TriviaService.getTrivia(category);

    }


}
