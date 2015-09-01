package com.samhan.Fakes;

import com.samhan.Board;
import com.samhan.Marker;
import com.samhan.player.Player;

public class PlayerSpy implements Player {

    private final Marker marker;
    public int nextMoveTimesCalled;

    public PlayerSpy(Marker marker) {
        this.nextMoveTimesCalled = 0;
        this.marker = marker;
    }

    @Override
    public Marker getMarker() {
        return marker;
    }

    @Override
    public int nextMove(Board board) {
        nextMoveTimesCalled++;
        return 0;
    }
}
