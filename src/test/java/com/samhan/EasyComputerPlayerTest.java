package com.samhan;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EasyComputerPlayerTest {
  @Test
  public void choosesOnlyAvailableMove() {
    Board board = new Board(3, new Marker[]{
            Marker.X, Marker.O, Marker.X,
            Marker.X, Marker.O, Marker.X,
            Marker.O, Marker.X, Marker.EMPTY
    });
    EasyComputerPlayer computer = new EasyComputerPlayer(Marker.O);

    int computerMove = computer.nextMove(board);

    assertThat(computerMove, is(8));
  }
}
