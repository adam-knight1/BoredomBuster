package org.boredombuster.controller;

import org.boredombuster.chessengine.ChessEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chess")
public class ChessController {
    private final ChessEngineService chessEngineService;
    @Autowired
    public ChessController(ChessEngineService chessEngineService) {
        this.chessEngineService = chessEngineService;
    }
}
