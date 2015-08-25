package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;

public interface Player {
  Marker getMarker();

  int nextMove(Board board);
}
