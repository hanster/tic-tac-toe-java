package com.samhan.ui.console;

import com.samhan.Board;
import com.samhan.ui.Display;
import com.samhan.ui.PlayerInput;
import com.samhan.ui.UserInput;

import java.util.List;
import java.util.stream.Collectors;

public class ConsolePlayerInput implements PlayerInput {
    private final Display output;
    private final UserInput userInput;
    private Board board;

    public ConsolePlayerInput(UserInput userInput, Display output) {
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
        output.enterMovePrompt();
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
        output.invalidMoveMessage(board);
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