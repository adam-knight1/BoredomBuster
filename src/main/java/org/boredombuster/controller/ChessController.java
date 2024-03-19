package org.boredombuster.controller;

import org.boredombuster.chessengine.ChessEngineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/chess")
public class ChessController {
    private final ChessEngineService chessEngineService;
    private static final Logger log = LoggerFactory.getLogger(ChessController.class);

    @Autowired
    public ChessController(ChessEngineService chessEngineService) {
        this.chessEngineService = chessEngineService;
    }

    /**
     * Starts the chess engine by calling the method in ChessEngineService
     *
     * @return validated for successful start or failed to start game
     */
    @PostMapping("/start")
    public ResponseEntity<String> startGame() {
        try {
            if (!chessEngineService.isEngineRunning()) {
                chessEngineService.startEngine();
            }
            return ResponseEntity.ok("Chess engine started successfully.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to start chess engine: " + e.getMessage());
        }
    }

    /**
     * Start a new game method, calling the start method in ChessEngineService"
     *
     * @return
     */
    @PostMapping("/startNewGame")
    public ResponseEntity<String> startNewGame() {
        try {
            chessEngineService.startNewGame();
            return ResponseEntity.ok("New game started successfully.");
        } catch (IOException e) {
            log.error("Failed to start a new game", e);
            return ResponseEntity.badRequest().body("Failed to start a new game: " + e.getMessage());
        }
    }

    /**
     * Calls setup board with current positioning and calls best move method in service with depth
     * depth specifies how many moves ahead stockfish will look to calculate best move
     */
    @PostMapping("/move")
    public ResponseEntity<String> makeMove(@RequestParam String playerMove) {
        try {
            chessEngineService.updateGameState(playerMove); //update the game
            chessEngineService.setupBoardWithCurrentGameState();

            int depth = 10; //set depth for stockfish to look ahead for best move
            String bestMove = chessEngineService.calculateBestMove(depth);

            if (bestMove.equals("checkmate")) {
                return ResponseEntity.ok("Checkmate! Game over.");
            } else {
                chessEngineService.updateGameState(bestMove); // Update the game state with Stockfish's move
                return ResponseEntity.ok("Best move is: " + bestMove);
            }
        } catch (IOException e) {
            log.error("IO error", e);
            return ResponseEntity.internalServerError().body("Error processing move" + e.getMessage());
        }
    }
}
