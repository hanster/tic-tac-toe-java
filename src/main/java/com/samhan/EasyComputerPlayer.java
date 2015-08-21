package com.samhan;

import java.util.Random;

public class EasyComputerPlayer implements Player{
  private Marker marker;

  public EasyComputerPlayer(Marker marker) {
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
