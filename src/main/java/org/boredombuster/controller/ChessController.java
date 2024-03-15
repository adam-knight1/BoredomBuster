package org.boredombuster.controller;

import org.boredombuster.chessengine.ChessEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    /** starts the chess engine by calling the method in ChessEngineService
     *
     * @return validated for successful start or failed to start game
     */
    @PostMapping("/start")
    public ResponseEntity<String> startGame() {
        try {
            chessEngineService.startEngine(); //this may be redundant depending on how I want to handle in service
            return ResponseEntity.ok("Chess engine started success");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("failed to start game: " + e.getMessage());
        }
    }

    /** Calls setup board with current positioning and calls best move method in service with depth
     * Depth specifies how many moves ahead stockfish will look to calculate best move
     * @param moves
     * @param depth
     * @return Response with best more or failed to calculate move response
     */
    @PostMapping("/move")
    public ResponseEntity<String> makeMove(@RequestParam String moves, @RequestParam int depth) {
        try {
            chessEngineService.setupBoard(moves);
            String bestMove = chessEngineService.calculateBestMove(depth);
            return ResponseEntity.ok("Best move is: " + bestMove);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("failed to calculate move: " + e.getMessage());
        }
    }
}
