package com.samhan.ui.console;

import com.samhan.BoardType;
import com.samhan.PlayerType;
import com.samhan.ui.OptionMenu;
import com.samhan.ui.UserInput;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

public class ConsoleOptionMenu implements OptionMenu {
    private static final String INVALID_SELECTION = "Invalid Selection.";
    private static final String PLAY_AGAIN = "Do you want to play again? ";
    private static final String INVALID_ENTRY = "Invalid Entry. [Y]es or [N]o";
    private final PrintStream output;
    private final UserInput userInput;

    public ConsoleOptionMenu(UserInput userInput, PrintStream output) {
        this.userInput = userInput;
        this.output = output;
    }

    @Override
    public String getSelection(String prompt, Map<String, String> options) {
        displayPrompt(prompt);
        displayOptions(options);
        return readSelection(prompt, options);
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
        output.print(PLAY_AGAIN);
        return readSelectionYesNo();
    }


    private String getPrompt(String playerNumber) {
        return String.format("Enter a Player%s selection", playerNumber);
    }


    private void displayPrompt(String prompt) {
        output.println(prompt);
    }

    private String readSelection(String prompt, Map<String, String> options) {
        String selection = userInput.readInput();
        if (valid(selection, options)) {
            return selection;
        } else {
            displayError(INVALID_SELECTION);
            return getSelection(prompt, options);
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

    private boolean valid(String selection, Map<String, String> options) {
        return options.containsKey(selection);
    }

    private void displayOptions(Map<String, String> options) {
        for (Map.Entry<String, String> option : options.entrySet()) {
            output.println(option.getKey() + " - " + option.getValue());
        }
    }
}
