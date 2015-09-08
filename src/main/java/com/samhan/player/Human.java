package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;
import com.samhan.ui.PlayerInput;

public class Human implements Player {
    private final Marker marker;
    private final PlayerInput input;

    public Human(Marker marker, PlayerInput input) {
        this.marker = marker;
        this.input = input;
    }

    @Override
    public Marker getMarker() {
        return marker;
    }

    @Override
    public int nextMove(Board board) {
        return input.getMove(board);
    }
}
