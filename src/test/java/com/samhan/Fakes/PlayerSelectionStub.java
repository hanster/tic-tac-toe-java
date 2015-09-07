package com.samhan.fakes;

import com.samhan.PlayerType;
import com.samhan.ui.PlayerSelection;

import java.util.LinkedList;

public class PlayerSelectionStub implements PlayerSelection {
    private final LinkedList<PlayerType> playerTypes;

    public PlayerSelectionStub(LinkedList<PlayerType> playerTypes) {
        this.playerTypes = playerTypes;
    }

    @Override
    public PlayerType selectType(String playerNumber) {
        return playerTypes.remove();
    }
}