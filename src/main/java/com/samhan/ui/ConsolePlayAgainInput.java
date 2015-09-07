package com.samhan.ui;

import java.io.*;
import java.util.Arrays;

public class ConsolePlayAgainInput {
    private static final String ANSI_CLS = "\u001b[2J";
    private static final String ANSI_HOME = "\u001b[H";
    private static final String WELCOME_MESSAGE = "Welcome to TicTcToe\n\nTime to set up!\n";
    private static final String FAREWELL_MESSAGE = "\nThanks for playing!\n\nBye!";
    private static final String PLAY_AGAIN = "Do you want to play again? ";
    private static final String INVALID_ENTRY = "Invalid Entry. [Y]es or [N]o";
    private final PrintStream output;
    private final BufferedReader input;

    public ConsolePlayAgainInput(InputStream inputStream, PrintStream output) {
        this.input = new BufferedReader(new InputStreamReader(inputStream));
        this.output = output;
    }

    public boolean doPlayAgain() {
        displayPrompt();
        return readSelection();
    }

    public void greet() {
        output.print(ANSI_CLS + ANSI_HOME);
        output.println(WELCOME_MESSAGE);
    }

    public void farewell() {
        output.println(FAREWELL_MESSAGE);
    }

    private void displayPrompt() {
        output.print(PLAY_AGAIN);
    }

    private boolean readSelection() {
        String selection = readInput();
        if (valid(selection)) {
            return isYes(selection);
        } else {
            displayInputError();
            return doPlayAgain();
        }
    }

    private void displayInputError() {
        output.println(INVALID_ENTRY);
    }

    private String readInput() {
        try {
            return input.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean valid(String selection) {
        return Arrays.asList("y", "n", "yes", "no").contains(selection.toLowerCase());
    }

    private boolean isYes(String selection) {
        return Arrays.asList("y", "yes").contains(selection.toLowerCase());
    }
}
