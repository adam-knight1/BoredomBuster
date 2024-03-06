package org.informationblitz.controller;

import org.informationblitz.dto.TriviaDTO;
import org.informationblitz.service.TriviaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/api/trivia")
public class TriviaController {
    private static final Logger logger = LoggerFactory.getLogger(TriviaController.class);
    @Autowired
    private TriviaService triviaService;

    @GetMapping("/question")
    public ResponseEntity<?> getTriviaQuestion(@RequestParam String category) {
        //modifying ? wildcard to account for response entity strings
        try {
            TriviaDTO trivia = triviaService.getTrivia(category);
            return ResponseEntity.ok(trivia);
        } catch (IOException e) {
            // Logging the exception details for debugging purposes
            logger.error("Error fetching trivia: {}", e.getMessage());
            // Returning error response;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching trivia, please try again later.");
        } catch (ResponseStatusException e) {
            // Logging and returning the specific status code and reason of the ResponseStatusException
            logger.error("Error with response status: {}", e.getStatus());
            return ResponseEntity.status(e.getStatus()).body(e.getReason());
        }
    }
}
