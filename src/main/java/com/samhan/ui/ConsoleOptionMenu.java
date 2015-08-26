package com.samhan.ui;

import java.io.*;
import java.util.Map;

public class ConsoleOptionMenu {
  private final BufferedReader input;
  private final PrintStream output;
  private String promptMessage;
  private Map<String, String> options;

  public ConsoleOptionMenu(InputStream inputStream, PrintStream output) {
    this.input = new BufferedReader(new InputStreamReader(inputStream));
    this.output = output;
  }

  public String getSelection(String prompt, Map<String, String> options) {
    this.promptMessage = prompt;
    this.options = options;
    output.println(promptMessage);
    displayOptions();
    return readSelection();
  }

  private String readSelection() {
    String selection = readInput();
    if (valid(selection)) {
      return selection;
    } else {
      displayError("Invalid Selection.");
      return getSelection(promptMessage, options);
    }
  }

  private String readInput() {
    try {
      return input.readLine();
    } catch (IOException e) {
      throw new RuntimeException(e);
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
