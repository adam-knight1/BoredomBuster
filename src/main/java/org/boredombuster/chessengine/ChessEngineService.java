package org.boredombuster.chessengine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.stereotype.Service;

import java.io.*;

//This class will help to integrate the stockfish chess engine into the app
@Service
public class ChessEngineService {

    //initializing stockfish and UCI components
    private Process engineProcess;
    private BufferedReader reader;
    private BufferedWriter writer;
    @Value("${stockfish.path}")
    private String stockfishPath;


    public void startEngine() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(stockfishPath);
        this.reader = new BufferedReader(new InputStreamReader(engineProcess.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(engineProcess.getOutputStream()));

    }
}