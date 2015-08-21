package com.samhan;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BoardTest {
  @Test
  public void startsEmpty() {
    Board board = new Board();
    assertThat(board.isEmpty(), is(true));
  }

  @Test
  public void notEmptyAfterPlacingAMarker() {
    Board board = new Board();

    Board newBoard = board.placeAt(0, Marker.X);

    assertThat(newBoard.isEmpty(), is(false));
  }

  @Test
  public void positionIsNotAvailableAfterPlacingAMarker() {
    Board board = new Board();
    assertThat(board.isAvailable(0), is(true));

    Board newBoard = board.placeAt(0, Marker.X);

    assertThat(newBoard.isAvailable(0), is(false));
  }

  @Test
  public void listAvailableMoves() {
    Board board = new Board();

    Board newBoard = board.placeAt(5, Marker.X);

    assertThat(newBoard.availableMoves(), CoreMatchers.<List>is(Arrays.asList(0, 1, 2, 3, 4, 6, 7, 8)));
  }

  @Test
  public void anEmptyFourBoard() {
    Board board = new Board(4);

    assertThat(board.availableMoves(), CoreMatchers.<List>is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)));
  }

  @Test
  public void startsWithNoWinner() {
    Board board = new Board();

    assertThat(board.hasWinner(), is(false));
  }

  @Test
  public void getSizeOfBoard() {
    Board threeBoard = new Board();
    Board fourBoard = new Board(4);

    assertThat(threeBoard.size(), is(3));
    assertThat(fourBoard.size(), is(4));
  }

  @Test
  public void winningTopHorizontalRow() {
    Board board = new Board(3, new Marker[]{
            Marker.X, Marker.X, Marker.X,
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY,
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY
    });

    assertThat(board.hasWinner(), is(true));
    assertThat(board.getWinner(), is(Marker.X));
  }

  @Test
  public void winningMiddleHorizontalRow() {
    Board board = new Board(3, new Marker[]{
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY,
            Marker.X, Marker.X, Marker.X,
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY
    });

    assertThat(board.hasWinner(), is(true));
    assertThat(board.getWinner(), is(Marker.X));
  }


  @Test
  public void winningBottomHorizontalRowFourBoard() {
    Board board = new Board(4, new Marker[]{
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY, Marker.EMPTY,
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY, Marker.EMPTY,
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY, Marker.EMPTY,
            Marker.X, Marker.X, Marker.X, Marker.X
    });

    assertThat(board.hasWinner(), is(true));
    assertThat(board.getWinner(), is(Marker.X));
  }

  @Test
  public void winningLeftColumn() {
    Board board = new Board(3, new Marker[]{
            Marker.X, Marker.EMPTY, Marker.EMPTY,
            Marker.X, Marker.EMPTY, Marker.EMPTY,
            Marker.X, Marker.EMPTY, Marker.EMPTY
    });

    assertThat(board.hasWinner(), is(true));
    assertThat(board.getWinner(), is(Marker.X));
  }


  @Test
  public void winningRightColumnFourBoard() {
    Board board = new Board(4, new Marker[]{
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY, Marker.X,
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY, Marker.X,
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY, Marker.X,
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY, Marker.X
    });

    assertThat(board.hasWinner(), is(true));
    assertThat(board.getWinner(), is(Marker.X));
  }

  @Test
  public void winningDiagonalLeftRight() {
    Board board = new Board(3, new Marker[]{
            Marker.X,     Marker.EMPTY, Marker.EMPTY,
            Marker.EMPTY, Marker.X,     Marker.EMPTY,
            Marker.EMPTY, Marker.EMPTY, Marker.X
    });

    assertThat(board.hasWinner(), is(true));
    assertThat(board.getWinner(), is(Marker.X));
  }


  @Test
  public void winningDiagonalRightLeftFourBoard() {
    Board board = new Board(4, new Marker[]{
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY, Marker.X,
            Marker.EMPTY, Marker.EMPTY, Marker.X, Marker.EMPTY,
            Marker.EMPTY, Marker.X,     Marker.EMPTY, Marker.EMPTY,
            Marker.X,     Marker.EMPTY, Marker.EMPTY, Marker.EMPTY
    });

    assertThat(board.hasWinner(), is(true));
    assertThat(board.getWinner(), is(Marker.X));
  }

  @Test
  public void gameIsOverWithAWinner() {
    Board board = new Board(3, new Marker[]{
            Marker.X, Marker.X, Marker.X,
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY,
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY
    });

    assertThat(board.isFinished(), is(true));
  }

  @Test
  public void boardIsDrawn() {
    Board board = new Board(3, new Marker[]{
            Marker.X, Marker.O, Marker.X,
            Marker.X, Marker.O, Marker.X,
            Marker.O, Marker.X, Marker.O
    });

    assertThat(board.hasWinner(), is(false));
    assertThat(board.isDraw(), is(true));
  }
}
