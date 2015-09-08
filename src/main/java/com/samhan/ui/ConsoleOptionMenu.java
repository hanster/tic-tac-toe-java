package com.samhan.ui;

import java.io.*;
import java.util.Map;

public class ConsoleOptionMenu implements OptionMenu {
    private static final String INVALID_SELECTION = "Invalid Selection.";
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
