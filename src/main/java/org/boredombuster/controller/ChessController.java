package org.boredombuster.controller;

import org.boredombuster.chessengine.ChessEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/chess")
public class ChessController {
    private final ChessEngineService chessEngineService;
    @Autowired
    public ChessController(ChessEngineService chessEngineService) {
        this.chessEngineService = chessEngineService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> makeMove(@RequestParam String moves, @RequestParam int depth) {
        try {
            chessEngineService.setupBoard(moves);
            String bestMove = chessEngineService.calculateBestMove(depth);
            return ResponseEntity.ok("Best move is: " + bestMove);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("failed to calculate move" + e.getMessage());
        }
    }
}
