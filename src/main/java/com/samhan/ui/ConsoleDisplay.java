package com.samhan.ui;

import com.samhan.Board;
import com.samhan.Marker;

import java.io.PrintStream;

import static java.lang.String.format;

public class ConsoleDisplay implements Display {
    private static final String ANSI_CLS = "\u001b[2J";
    private static final String ANSI_HOME = "\u001b[H";
    private static final String WELCOME_MESSAGE = "Welcome to TicTcToe\n\nTime to set up!\n";
    private static final String FAREWELL_MESSAGE = "\nThanks for playing!\n\nBye!";
    private static final String CURRENT_PLAYER = "Current Player: %s";
    private static final String GAME_OVER_RESULT = "Game Over! Result: %s";
    private static final String DRAW = "Draw";
    private static final String WINS = " wins";
    private static final String INVALID_ENTRY = "Invalid Entry (1-%s)";
    private static final String ENTER_MOVE = "Enter move: ";
    private final PrintStream output;

    public ConsoleDisplay(PrintStream output) {
        this.output = output;
    }

    @Override
    public void render(Board board, Marker marker) {
        clearDisplay();
        renderBoard(board);
        renderStatus(board, marker);
    }

    @Override
    public void greet() {
        output.print(ANSI_CLS + ANSI_HOME);
        output.println(WELCOME_MESSAGE);
    }

    @Override
    public void farewell() {
        output.println(FAREWELL_MESSAGE);
    }

    @Override
    public void enterMovePrompt() {
        output.print(ENTER_MOVE);
    }

    @Override
    public void invalidMoveMessage(Board board) {
        output.println(String.format(INVALID_ENTRY, board.size() * board.size()));
    }

    private void renderStatus(Board board, Marker marker) {
        displayBlankLine();
        if (!board.isFinished()) {
            renderOngoingStatus(marker);
        } else {
            renderEndStatus(board);
        }
        displayBlankLine();
    }

    private void renderOngoingStatus(Marker marker) {
        output.println(format(CURRENT_PLAYER, marker.toString()));
    }

    private void renderEndStatus(Board board) {
        output.println(endStatusMessage(board));
    }

    private String endStatusMessage(Board board) {
        return format(GAME_OVER_RESULT, getResult(board));
    }

    private String getResult(Board board) {
        return board.getWinner()
                .map(winner -> winner.toString() + WINS)
                .orElse(DRAW);
    }

    private void clearDisplay() {
        output.print(ANSI_CLS + ANSI_HOME);
    }

    private void renderBoard(Board board) {
        displayBlankLine();
        displayBoard(board);
        displayBlankLine();
    }

    private void displayBoard(Board board) {
        BoardPresenter presenter = new BoardPresenter(board);
        output.println(presenter.render());
    }

    private void displayBlankLine() {
        output.print("\n");
    }
}
