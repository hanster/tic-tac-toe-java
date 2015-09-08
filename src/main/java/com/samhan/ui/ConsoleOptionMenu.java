package com.samhan.ui;

import com.samhan.BoardType;
import com.samhan.PlayerType;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

public class ConsoleOptionMenu implements OptionMenu {
    private static final String INVALID_SELECTION = "Invalid Selection.";
    private static final String PLAY_AGAIN = "Do you want to play again? ";
    private static final String INVALID_ENTRY = "Invalid Entry. [Y]es or [N]o";
    private final PrintStream output;
    private String promptMessage;
    private Map<String, String> options;
    private final UserInput userInput;

    public ConsoleOptionMenu(UserInput userInput, PrintStream output) {
        this.userInput = userInput;
        this.output = output;
    }

    @Override
    public String getSelection(String prompt, Map<String, String> options) {
        this.promptMessage = prompt;
        this.options = options;

        displayPrompt();
        displayOptions();
        return readSelection();
    }

    @Override
    public BoardType getBoardSelection() {
        String boardSelection = getSelection("Enter Board size selection", BoardType.options());
        return BoardType.getType(boardSelection);
    }

    @Override
    public PlayerType getPlayerSelection(String playerNumber) {
        String playerSelection = getSelection(getPrompt(playerNumber), PlayerType.options());
        return PlayerType.getType(playerSelection);
    }

    @Override
    public boolean doPlayAgain() {
        output.print(PLAY_AGAIN);;
        return readSelectionYesNo();
    }


    private String getPrompt(String playerNumber) {
        return String.format("Enter a Player%s selection", playerNumber);
    }


    private void displayPrompt() {
        output.println(promptMessage);
    }

    private String readSelection() {
        String selection = userInput.readInput();
        if (valid(selection)) {
            return selection;
        } else {
            displayError(INVALID_SELECTION);
            return getSelection(promptMessage, options);
        }
    }

    private boolean readSelectionYesNo() {
        String selection = userInput.readInput();
        if (validYesNo(selection)) {
            return isYes(selection);
        } else {
            displayInputError();
            return doPlayAgain();
        }
    }

    private void displayInputError() {
        output.println(INVALID_ENTRY);
    }

    private boolean validYesNo(String selection) {
        return Arrays.asList("y", "n", "yes", "no").contains(selection.toLowerCase());
    }

    private boolean isYes(String selection) {
        return Arrays.asList("y", "yes").contains(selection.toLowerCase());
    }

    private void displayError(String errorMessage) {
        output.println(errorMessage);
    }

    private boolean valid(String selection) {
        return options.containsKey(selection);
    }

    private void displayOptions() {
        for (Map.Entry<String, String> option : options.entrySet()) {
            output.println(option.getKey() + " - " + option.getValue());
        }
    }
}
