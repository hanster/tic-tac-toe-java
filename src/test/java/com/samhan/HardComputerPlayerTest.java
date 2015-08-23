package com.samhan;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HardComputerPlayerTest {
  @Test
  public void hasAMarker() {
    HardComputerPlayer computer = new HardComputerPlayer(Marker.O);

    assertThat(computer.getMarker(), is(Marker.O));
  }

  @Test
  public void makesAValidMove() {
    Board emptyBoard = new Board();
    HardComputerPlayer computer = new HardComputerPlayer(Marker.O);

    int computerMove = computer.nextMove(emptyBoard);

    assertThat(emptyBoard.isAvailable(computerMove), is(true));
  }

  @Test
  public void choosesOnlyAvailableMove() {
    Board board = new Board(3, new Marker[]{
            Marker.X, Marker.O, Marker.X,
            Marker.X, Marker.O, Marker.X,
            Marker.O, Marker.X, Marker.EMPTY
    });
    HardComputerPlayer computer = new HardComputerPlayer(Marker.O);

    int computerMove = computer.nextMove(board);

    assertThat(computerMove, is(8));
  }

  @Test
  public void chooseWinningMoveOutOf2Moves() {
    Board board = new Board(3, new Marker[]{
            Marker.X, Marker.O,     Marker.X,
            Marker.X, Marker.O,     Marker.X,
            Marker.O, Marker.EMPTY, Marker.EMPTY
    });
    HardComputerPlayer computer = new HardComputerPlayer(Marker.X);

    assertThat(computer.nextMove(board), is(8));
  }

  @Test
  public void choosesBlockingMove() {
    Board board = new Board(3, new Marker[]{
            Marker.X, Marker.O,     Marker.X,
            Marker.O, Marker.X,     Marker.X,
            Marker.O, Marker.EMPTY, Marker.EMPTY
    });
    HardComputerPlayer computer = new HardComputerPlayer(Marker.O);

    assertThat(computer.nextMove(board), is(8));
  }

  @Test
  public void testSlowMoves() {
    Board board = new Board(4, new Marker[]{
            Marker.X,     Marker.EMPTY, Marker.EMPTY, Marker.EMPTY,
            Marker.EMPTY, Marker.X,     Marker.EMPTY, Marker.EMPTY,
            Marker.EMPTY, Marker.EMPTY, Marker.X,     Marker.EMPTY,
            Marker.EMPTY, Marker.O,     Marker.O,     Marker.EMPTY,
    });
    HardComputerPlayer computer = new HardComputerPlayer(Marker.O);

    int computerMove = computer.nextMove(board);

    assertThat(computerMove, is(15));
  }

  @Test
  public void testsRandomMove() {
    Board board = new Board(4, new Marker[]{
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY, Marker.EMPTY,
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY, Marker.EMPTY,
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY, Marker.EMPTY,
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY, Marker.EMPTY,
    });
    HardComputerPlayer computer = new HardComputerPlayer(Marker.O);

    int computerMove = computer.nextMove(board);
    List<Integer> availableMoves = board.availableMoves();

    assertThat(availableMoves, hasItem(computerMove));
  }
}
