package com.samhan.fakes;


import com.samhan.Board;
import com.samhan.ui.PlayerInputOutput;

public class PlayerInputOutputSpy implements PlayerInputOutput {
    public int getMoveTimesCalled;

    public PlayerInputOutputSpy() {
        getMoveTimesCalled = 0;
    }

    @Override

    public int getMove(Board board) {
        getMoveTimesCalled++;
        return getMoveTimesCalled;
    }
}
