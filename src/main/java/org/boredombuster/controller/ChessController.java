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

    /** starts the chess engine by calling the method in ChessEngineService
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
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            log.error("Calculation error", cause);
            return ResponseEntity.internalServerError().body("Error calculating the best move: " + cause.getMessage());
        } catch (InterruptedException e) {
            log.error("Calculation was interrupted", e);
            Thread.currentThread().interrupt();
            return ResponseEntity.internalServerError().body("Calculation was interrupted.");
        } catch (TimeoutException e) {
            log.error("Calculation timed out", e);
            return ResponseEntity.internalServerError().body("Calculation timed out.");
        } catch (IOException e) {
            log.error("IO error", e);
            return ResponseEntity.internalServerError().body("IO error while calculating the best move: " + e.getMessage());
        }
    }
}