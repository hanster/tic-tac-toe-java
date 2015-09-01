package com.samhan.ui;

import com.samhan.Board;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ConsolePlayerInputOutput implements PlayerInputOutput {
    private static final String INVALID_ENTRY = "Invalid Entry (1-%s)";
    private static final String ENTER_MOVE = "Enter move: ";
    private final PrintStream output;
    private final BufferedReader input;
    private Board board;

    public ConsolePlayerInputOutput(InputStream inputStream, PrintStream output) {
        this.input = new BufferedReader(new InputStreamReader(inputStream));
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
        String selection = readInput();
        if (valid(selection)) {
            return Integer.parseInt(selection) - 1;
        } else {
            displayInputError();
            return getMove(board);
        }
    }

    private void displayInputError() {
        output.println(String.format(INVALID_ENTRY, board.size() * board.size()));
    }

    private String readInput() {
        try {
            return input.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean valid(String selection) {
        List<String> validSelections = validSelections();
        return validSelections.contains(selection);
    }

    private List<String> validSelections() {
        return board.availableMoves().stream()
                .map(availableMove -> String.valueOf(availableMove + 1))
                .collect(Collectors.toList());
    }
}