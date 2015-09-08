package com.samhan.fakes;

import com.samhan.BoardType;
import com.samhan.PlayerType;
import com.samhan.ui.OptionMenu;

import java.util.LinkedList;
import java.util.Map;

public class OptionMenuStub implements OptionMenu {
    private final String selection;
    private BoardType boardType;
    private LinkedList<PlayerType> playerTypes;

    public OptionMenuStub(String selection) {
        this.selection = selection;
    }

    @Override
    public String getSelection(String prompt, Map<String, String> options) {
        return selection;
    }

    @Override
    public BoardType getBoardSelection() {
        return boardType;
    }

    @Override
    public PlayerType getPlayerSelection(String player) {
        return playerTypes.remove();
    }

    @Override
    public boolean doPlayAgain() {
        return false;
    }

    public void setBoardSelection(BoardType boardType) {
        this.boardType = boardType;
    }

    public void setPlayersSelection(LinkedList<PlayerType> playerTypes) {
        this.playerTypes = playerTypes;
    }
}
