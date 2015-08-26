package com.samhan;

import java.util.LinkedHashMap;
import java.util.Map;

public enum BoardType {
  THREE("3", "Three By Three"), FOUR("4", "Four By Four");

  private final String displayText;
  private final String input;

  BoardType(String input, String displayText) {
    this.input = input;
    this.displayText = displayText;
  }

  public String getInput() {
    return input;
  }

  public static Map<String, String> options() {
    Map<String, String> options = new LinkedHashMap<>();
    for (BoardType boardType : BoardType.values()) {
      options.put(boardType.input, boardType.displayText);
    }
    return options;
  }

  public static BoardType getType(String consoleInput) {
    for (BoardType boardType : BoardType.values()) {
      if (boardType.input.equals(consoleInput)) {
        return boardType;
      }
    }
    throw new RuntimeException("Unexpected Board Type");
  }
}
