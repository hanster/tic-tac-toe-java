package com.samhan.ui;

import com.samhan.Board;
import com.samhan.Marker;

public interface Display {
    void render(Board board, Marker marker);
    void greet();
    void farewell();
    void enterMovePrompt();
    void invalidMoveMessage(Board board);

}
