package com.samhan.ui;

import com.samhan.PlayerType;

public class ConsolePlayerSelection {
  private final OptionMenu optionMenu;

  public ConsolePlayerSelection(OptionMenu optionMenu) {
    this.optionMenu = optionMenu;
  }

  public PlayerType getPlayerSelection() {
    String playerSelection = optionMenu.getSelection("Enter a Player selection", PlayerType.options());
    return PlayerType.getType(playerSelection);
  }
}
