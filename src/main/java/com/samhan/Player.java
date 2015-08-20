package com.samhan;

public interface Player {
  Marker getMarker();

  int nextMove(Board board);
}
