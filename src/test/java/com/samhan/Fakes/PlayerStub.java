package com.samhan.fakes;

import com.samhan.Board;
import com.samhan.Marker;
import com.samhan.player.Player;

import java.util.LinkedList;

public class PlayerStub implements Player {
    private final Marker marker;
    private final LinkedList<Integer> moves;
    private int nextMoveTimesCalled;

    public PlayerStub(Marker marker, LinkedList<Integer> moves) {
        this.marker = marker;
        this.moves = moves;
        this.nextMoveTimesCalled = 0;
    }

    @Override
    public Marker getMarker() {
        return marker;
    }

    @Override
    public int nextMove(Board board) {
        this.nextMoveTimesCalled++;
        return moves.remove();
    }

    public int getNextMoveTimesCalled() {
        return nextMoveTimesCalled;
    }
}
