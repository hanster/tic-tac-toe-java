package com.samhan.Fakes;

import com.samhan.Board;
import com.samhan.Marker;
import com.samhan.ui.Display;

public class DisplaySpy implements Display {
  public int renderTimesCalled;

  public DisplaySpy() {
    renderTimesCalled = 0;
  }

  @Override
  public void render(Board board, Marker marker) {
    renderTimesCalled++;
  }
}