package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;
import com.samhan.ui.PlayerInputOuput;

public class HumanPlayer implements Player {
  private Marker marker;
  private PlayerInputOuput input;

  public HumanPlayer(Marker marker, PlayerInputOuput input) {
    this.marker = marker;
    this.input = input;
  }

  @Override
  public Marker getMarker() {
    return marker;
  }

  @Override
  public int nextMove(Board board) {
    return input.getMove(board);
  }
}
