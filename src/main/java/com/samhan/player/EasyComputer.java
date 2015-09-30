package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;

import java.util.Random;

public class EasyComputer implements Player {
    private final Marker marker;

    public EasyComputer(Marker marker) {
        this.marker = marker;
    }

    @Override
    public Marker getMarker() {
        return marker;
    }

    @Override
    public int nextMove(Board board) {
        int idx = new Random().nextInt(board.availableMoves().size());
        return board.availableMoves().get(idx);
    }
}
