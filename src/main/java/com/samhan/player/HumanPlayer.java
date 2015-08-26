package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;
import com.samhan.ui.PlayerInputOutput;

public class HumanPlayer implements Player {
  private Marker marker;
  private PlayerInputOutput input;

  public HumanPlayer(Marker marker, PlayerInputOutput input) {
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
