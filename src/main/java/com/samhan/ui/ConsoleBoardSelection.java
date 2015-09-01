package com.samhan.ui;

import com.samhan.BoardType;

public class ConsoleBoardSelection implements BoardSelection {
    private final OptionMenu optionMenu;

    public ConsoleBoardSelection(OptionMenu optionMenu) {
        this.optionMenu = optionMenu;
    }

    @Override
    public BoardType selectType() {
        String boardSelection = optionMenu.getSelection("Enter Board size selection", BoardType.options());
        return BoardType.getType(boardSelection);
    }
}
