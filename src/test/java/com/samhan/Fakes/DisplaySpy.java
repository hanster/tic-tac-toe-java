package com.samhan.fakes;

import com.samhan.Board;
import com.samhan.Marker;
import com.samhan.ui.Display;

public class DisplaySpy implements Display {
    private int renderTimesCalled;

    public DisplaySpy() {
        renderTimesCalled = 0;
    }

    @Override
    public void render(Board board, Marker marker) {
        renderTimesCalled++;
    }

    @Override
    public void greet() {

    }

    @Override
    public void farewell() {

    }

    @Override
    public void enterMovePrompt() {

    }

    @Override
    public void invalidMoveMessage(Board board) {

    }

    public int getRenderTimesCalled() {
        return renderTimesCalled;
    }
}