package com.samhan;

public class HumanPlayer implements Player {
  private Marker marker;
  private PlayerInput input;

  public HumanPlayer(Marker marker, PlayerInput input) {
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
