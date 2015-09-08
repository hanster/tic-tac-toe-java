package com.samhan.ui;

import com.samhan.Board;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ConsolePlayerInput implements PlayerInputOutput {
    private static final String INVALID_ENTRY = "Invalid Entry (1-%s)";
    private static final String ENTER_MOVE = "Enter move: ";
    private final PrintStream output;
    private final UserInput userInput;
    private Board board;

    public ConsolePlayerInput(UserInput userInput, PrintStream output) {
        this.userInput = userInput;
        this.output = output;
    }

    @Override
    public int getMove(Board board) {
        this.board = board;

        displayPrompt();
        return readSelection();
    }

    private void displayPrompt() {
        output.print(ENTER_MOVE);
    }

    private int readSelection() {
        String selection = userInput.readInput();
        if (valid(selection)) {
            return Integer.parseInt(selection);
        } else {
            displayInputError();
            return getMove(board);
        }
    }

    private void displayInputError() {
        output.println(String.format(INVALID_ENTRY, board.size() * board.size()));
    }

    private boolean valid(String selection) {
        List<String> validSelections = validSelections();
        return validSelections.contains(selection);
    }

    private List<String> validSelections() {
        return board.availableMoves().stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}