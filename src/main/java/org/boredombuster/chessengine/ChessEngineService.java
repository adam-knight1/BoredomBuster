package org.boredombuster.chessengine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.*;

/**
 * This class will help to integrate the stockfish chess engine into the app
 * It contains methods for reading and writing commands to/from the stockfish engine, and starting/stopping the engine
 */
@Service
public class ChessEngineService {

    //referenced the documentation here to understand UCI protocol
    //https://gist.github.com/DOBRO/2592c6dad754ba67e6dcaec8c90165bf

    //initializing stockfish and UCI components
    private Process engineProcess;
    private BufferedReader reader;
    private BufferedWriter writer;
    @Value("${stockfish.path}")
    private String stockfishPath;

    /**
     * Method to start the stockfish engine
     * Configures the engine, sends UCI command to set up stockfish with UCI protocol
     * Checks response for UCI OK and successful engine start
     *
     * @throws IOException
     */
    public void startEngine() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(stockfishPath);
        this.engineProcess = builder.start(); //start the engine
        this.reader = new BufferedReader(new InputStreamReader(engineProcess.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(engineProcess.getOutputStream()));

        sendCommand("uci"); //telling stockfish to use UCI protocol (Universal Chess Interface)

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.equals("uciok")) { //response UCI OK, stockfish ready in UCI mode
                break;
            }
        }

        //may need to set more command options here?

        sendCommand("isReady");
        while ((line = reader.readLine()) != null) {
            if (line.equals("readyok")) {
                break;  //stockfish fully initialized, ready to go
            }
        }
    }

    /**
     * Read output from engine
     *
     * @return
     * @throws IOException
     */
    public String readOutput() throws IOException {
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");

            if (line.contains("bestmove")) {
                break;
            }
        }
        return output.toString();
    }

    /**
     * Stop the stockfish engine
     **/
    @PreDestroy
    public void stopEngine() {
        if (engineProcess != null) {
            engineProcess.destroy();
        }
    }

    /**
     * Send commands to stockfish service
     *
     * @param command
     * @throws IOException
     */
    public void sendCommand(String command) throws IOException {
        writer.write(command);
        writer.newLine();
        writer.flush();
    }

    /**
     * Sets up the board with a given position.
     *
     * @param moves A string representing the moves made from the start position, separated by spaces.
     *              For example, "e2e4 e7e5" sets up board with those moves made from the starting position.
     * @throws IOException If there's an error communicating with the Stockfish process.
     */

    public void setupBoard(String moves) throws IOException {
        sendCommand("position startpos moves" + moves); //starting from standard chess position

        //I may not need this second ready check
        String line;
        sendCommand("isready");
        while ((line = reader.readLine()) != null) {
            if (line.equals("readyok")) {
                break;  //stockfish fully initialized, ready to go
            }
        }
    }

    /**
     * Asks Stockfish to calculate the best move based on the current board state.
     *
     * @param depth The depth of the search. Higher values increase calculation time but improve accuracy.
     * @return The best move as calculated by Stockfish.
     * @throws IOException If there's an error communicating with the Stockfish process.
     */

    public String calculateBestMove(int depth) throws IOException {
        sendCommand("go depth " + depth); //this depth should set how far ahead stockfish looks for move analysis.
        // More moves ahead is harder but requires more computation time.
        return readOutputUntilBestMove(); //
    }

    private String readOutputUntilBestMove() throws IOException {
        String line;
        String bestMove = null;

        while ((line = reader.readLine()) != null) { //will read lines of output from SF until no more lines
            if (line.startsWith("bestmove")) { //potential for error with camelCase
                bestMove = line.split(" ")[1]; //this should break up the response into array of strings and pull the 2nd index, which is the move
                break;
            }
        }
        return bestMove;
    }
}

