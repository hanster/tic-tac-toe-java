package com.samhan;

import java.util.LinkedHashMap;
import java.util.Map;

public enum PlayerType {
  HUMAN("1", "Human Player"), EASY_COMPUTER("2", "Easy Computer Player"), HARD_COMPUTER("3", "Hard Computer Player");

  private final String displayText;
  private final String input;

  PlayerType(String input, String displayText) {
    this.input = input;
    this.displayText = displayText;
  }

  public static Map<String, String> options() {
    Map<String, String> options = new LinkedHashMap<>();
    for (PlayerType playerType : PlayerType.values()) {
      options.put(playerType.input, playerType.displayText);
    }
    return options;
  }

  public static PlayerType getType(String consoleInput) {
    for (PlayerType playerType : PlayerType.values()) {
      if (playerType.input.equals(consoleInput)) {
        return playerType;
      }
    }
    return null;
  }
}
