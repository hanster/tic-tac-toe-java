package com.samhan.ui;

import com.samhan.BoardType;

public class ConsoleBoardSelection {
  private final OptionMenu optionMenu;

  public ConsoleBoardSelection(OptionMenu optionMenu) {
    this.optionMenu = optionMenu;
  }

  public BoardType getBoardSelection() {
    String boardSelection = optionMenu.getSelection("Enter Board size selection", BoardType.options());
    return BoardType.getType(boardSelection);
  }
}
