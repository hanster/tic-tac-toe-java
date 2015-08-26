package com.samhan.ui;

import com.samhan.PlayerType;

public class ConsolePlayerSelection implements PlayerSelection {
  private final OptionMenu optionMenu;

  public ConsolePlayerSelection(OptionMenu optionMenu) {
    this.optionMenu = optionMenu;
  }

  @Override
  public PlayerType select(String playerNumber) {
    String playerSelection = optionMenu.getSelection(getPrompt(playerNumber), PlayerType.options());
    return PlayerType.getType(playerSelection);
  }

  private String getPrompt(String playerNumber) {
    return String.format("Enter a Player%s selection", playerNumber);
  }
}
