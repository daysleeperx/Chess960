package stockfish;

import board.Board;
import game.Game;
import pieces.Color;
import player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import static utils.FenParser.parseToFen;

/**
 * Establishes a connection with Stockfish Chess engine.
 * All communication is done via standard input and output with text commands.
 */
public class StockFish extends Player {
    /**
     * Class constructor.
     *
     * @param color Color
     * @param game Game object
     */
    public StockFish(Color color, Game game) {
        this.color = color;
        this.game = game;
    }
    /**
     * Reader and write of input and output.
     */
    private BufferedReader bufferedReader;
    private OutputStreamWriter outputStreamWriter;

    /**
     * Absolute path to Stockfish.
     */
    private static final String PATH = "/Users/viktorpavlov1/Stockfish/src/stockfish";

    /**
     * Starts the engine and initializes the input/output streams.
     */
    public void startEngine() throws IOException {
        Process stockfish = Runtime.getRuntime().exec(PATH);
        bufferedReader = new BufferedReader(new InputStreamReader(stockfish.getInputStream()));
        outputStreamWriter = new OutputStreamWriter(stockfish.getOutputStream());
    }

    /**
     * Send UCI command to engine.
     *
     * @param command String
     */
    public void sendCommand(String command) {
        try {
            outputStreamWriter.write(command + "\n");
            outputStreamWriter.flush();
        } catch (IOException e) {
            System.out.println("Output error: " + e.getMessage());
        }
    }

    /**
     * Get output from Stockfish.
     *
     * @param wait int
     * @return String
     */
    public String getOutput(int wait) {
        StringBuilder out = new StringBuilder();
        try {
            Thread.sleep(wait);
            sendCommand("isready");
            while (true) {
                String text = bufferedReader.readLine();
                if (text.equals("readyok")) break;

                out.append(text).append("\n");
            }
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Output error: " + e.getMessage());
        }

        return out.toString();
    }

    /**
     * Return best move from Stockfish.
     *
     * @param fen  String (Chess position using the FEN notation)
     * @param wait int (millisecond indicating how long Stockfish should evaluate the position.
     * @return String (Algebraic notation)
     */
    public String getBestMove(String fen, int wait) {
        sendCommand("position fen " + fen);
        sendCommand("go movetime " + wait);

        return getOutput(wait + 100).split("bestmove ")[1].split(" ")[0].replace("\n", "");
    }

    /**
     * Closes Stockfish and output/ input streams.
     */
    public void stopEngine() {
        try {
            sendCommand("quit");
            bufferedReader.close();
            outputStreamWriter.close();
        } catch (IOException e) {
            System.out.println("Error :" + e.getMessage());
        }
    }
//
//    public static void main(String[] args) throws IOException {
//        StockFish stockFish = new StockFish();
//        stockFish.startEngine();
//
//        Scanner sc = new Scanner(System.in);
//
//        while (true) {
//            String command = sc.nextLine();
//            System.out.println(stockFish.getBestMove(command, 2000));
//        }
//    }

    /**
     * Move method.
     *
     * @param board Board object
     * @param sideToMove Color
     */
    @Override
    public String move(Board board, Color sideToMove) {
        return getBestMove(parseToFen(board, sideToMove), 1000);

    }
}
