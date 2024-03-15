package org.boredombuster.chessengine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.*;

/** This class will help to integrate the stockfish chess engine into the app
 * It contains methods for reading and writing commands to/from the stockfish engine, and starting/stopping the engine
 */
@Service
public class ChessEngineService {

    //initializing stockfish and UCI components
    private Process engineProcess;
    private BufferedReader reader;
    private BufferedWriter writer;
    @Value("${stockfish.path}")
    private String stockfishPath;

    /** Method to start the stockfish engine
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

    /** read output from engine
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

    /** Stop the stockfish engine **/
    @PreDestroy
    public void stopEngine() {
        if (engineProcess == null) {
            engineProcess.destroy();
        }
    }

    /** send commands to stockfish service
     *
     * @param command
     * @throws IOException
     */
    public void sendCommand(String command) throws IOException {
        writer.write(command);
        writer.newLine();
        writer.flush();
    }


        //setting up the board

        public void setupBoard (String moves) throws IOException {
        sendCommand("position startpos moves"); //starting from standard chess position

            //I may not need this second ready check
            String line;
            sendCommand("isReady");
            while ((line = reader.readLine()) != null) {
                if (line.equals("readyok")) {
                    break;  //stockfish fully initialized, ready to go
                }
            }
        }

        public void calculateBestMove(int depth) throws IOException {
        sendCommand("go depth" + depth);
        }

        private String readOutputUntilBestMove() throws IOException {
        String line;
        String bestMove = null;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("bestMove")) {
                bestMove = line.split(" ")[1];
                break;
            }
        }
        return bestMove;
        }
    }

