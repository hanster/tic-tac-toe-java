package com.samhan.fakes;


import com.samhan.Board;
import com.samhan.ui.PlayerInput;

public class PlayerInputSpy implements PlayerInput {
    private int getMoveTimesCalled;

    public PlayerInputSpy() {
        getMoveTimesCalled = 0;
    }

    @Override
    public int getMove(Board board) {
        getMoveTimesCalled++;
        return getMoveTimesCalled;
    }

    public int getMoveTimesCalled() {
        return getMoveTimesCalled;
    }
}
