package com.samhan;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.samhan.Marker.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoardTest {
    @Test
    public void startsEmpty() {
        Board board = new Board();
        assertThat(board.isEmpty(), is(true));
    }

    @Test
    public void notEmptyAfterPlacingAMarker() {
        Board board = new Board();

        Board newBoard = board.placeAt(1, X);

        assertThat(newBoard.isEmpty(), is(false));
    }

    @Test
    public void positionIsNotAvailableAfterPlacingAMarker() {
        Board board = new Board();
        assertThat(board.isAvailable(1), is(true));

        Board newBoard = board.placeAt(1, X);

        assertThat(newBoard.isAvailable(1), is(false));
    }

    @Test
    public void listAvailableMoves() {
        Board board = new Board();

        Board newBoard = board.placeAt(5, X);

        assertThat(newBoard.availableMoves(), CoreMatchers.<List>is(Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9)));
    }

    @Test
    public void anEmptyFourBoard() {
        Board board = new Board(4);

        assertThat(board.availableMoves(), CoreMatchers.<List>is(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)));
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
                X,     X,     X,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY
        });

        assertThat(board.hasWinner(), is(true));
        assertThat(board.getWinner(), is(X));
    }

    @Test
    public void winningMiddleHorizontalRow() {
        Board board = new Board(3, new Marker[]{
                EMPTY, EMPTY, EMPTY,
                X,     X,     X,
                EMPTY, EMPTY, EMPTY
        });

        assertThat(board.hasWinner(), is(true));
        assertThat(board.getWinner(), is(X));
    }


    @Test
    public void winningBottomHorizontalRowFourBoard() {
        Board board = new Board(4, new Marker[]{
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
                X,     X,     X,     X
        });

        assertThat(board.hasWinner(), is(true));
        assertThat(board.getWinner(), is(X));
    }

    @Test
    public void winningLeftColumn() {
        Board board = new Board(3, new Marker[]{
                X, EMPTY, EMPTY,
                X, EMPTY, EMPTY,
                X, EMPTY, EMPTY
        });

        assertThat(board.hasWinner(), is(true));
        assertThat(board.getWinner(), is(X));
    }


    @Test
    public void winningRightColumnFourBoard() {
        Board board = new Board(4, new Marker[]{
                EMPTY, EMPTY, EMPTY, X,
                EMPTY, EMPTY, EMPTY, X,
                EMPTY, EMPTY, EMPTY, X,
                EMPTY, EMPTY, EMPTY, X
        });

        assertThat(board.hasWinner(), is(true));
        assertThat(board.getWinner(), is(X));
    }

    @Test
    public void winningDiagonalLeftRight() {
        Board board = new Board(3, new Marker[]{
                X,     EMPTY, EMPTY,
                EMPTY, X,     EMPTY,
                EMPTY, EMPTY, X
        });

        assertThat(board.hasWinner(), is(true));
        assertThat(board.getWinner(), is(X));
    }


    @Test
    public void winningDiagonalRightLeftFourBoard() {
        Board board = new Board(4, new Marker[]{
                EMPTY, EMPTY, EMPTY, X,
                EMPTY, EMPTY, X,     EMPTY,
                EMPTY, X,     EMPTY, EMPTY,
                X,     EMPTY, EMPTY, EMPTY
        });

        assertThat(board.hasWinner(), is(true));
        assertThat(board.getWinner(), is(X));
    }

    @Test
    public void gameIsOverWithAWinner() {
        Board board = new Board(3, new Marker[]{
                X,     X,     X,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY
        });

        assertThat(board.isFinished(), is(true));
    }

    @Test
    public void boardIsDrawn() {
        Board board = new Board(3, new Marker[]{
                X, O, X,
                X, O, X,
                O, X, O
        });

        assertThat(board.hasWinner(), is(false));
        assertThat(board.isDraw(), is(true));
    }
}
