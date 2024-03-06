package org.informationblitz.controller;

import org.informationblitz.dto.TriviaDTO;
import org.informationblitz.service.TriviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/trivia")
public class TriviaController {

    @Autowired
    private TriviaService triviaService;

    @GetMapping("/trivia")
    public ResponseEntity<TriviaDTO> getTriviaQuestion(@RequestParam String category) {
        try {
            TriviaDTO trivia = TriviaService.getTrivia(category);
            return ResponseEntity.ok(trivia);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(null); //this sends a null body back as an exception was caught
        }
    }
}
