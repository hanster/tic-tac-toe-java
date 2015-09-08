package com.samhan;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.samhan.BoardCreationHelper.*;
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

        Board newBoard = board.placeAt(1, Marker.X);

        assertThat(newBoard.isEmpty(), is(false));
    }

    @Test
    public void positionIsNotAvailableAfterPlacingAMarker() {
        Board board = new Board();
        assertThat(board.isAvailable(1), is(true));

        Board newBoard = board.placeAt(1, Marker.X);

        assertThat(newBoard.isAvailable(1), is(false));
    }

    @Test
    public void listAvailableMoves() {
        Board board = new Board();

        Board newBoard = board.placeAt(5, Marker.X);

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

        assertHasWinner(board, false);
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
        Board board = createBoard(3,
                X,     X,     X,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY
        );

        assertHasWinner(board, true);
        assertWinnerIsX(board);
    }

    @Test
    public void winningMiddleHorizontalRow() {
        Board board = createBoard(3,
                EMPTY, EMPTY, EMPTY,
                X,     X,     X,
                EMPTY, EMPTY, EMPTY
        );

        assertHasWinner(board, true);
        assertWinnerIsX(board);
    }


    @Test
    public void winningBottomHorizontalRowFourBoard() {
        Board board = createBoard(4,
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
                X,     X,     X,     X
        );

        assertHasWinner(board, true);
        assertWinnerIsX(board);
    }

    @Test
    public void winningLeftColumn() {
        Board board = createBoard(3,
                X, EMPTY, EMPTY,
                X, EMPTY, EMPTY,
                X, EMPTY, EMPTY
        );

        assertHasWinner(board, true);
        assertWinnerIsX(board);
    }


    @Test
    public void winningRightColumnFourBoard() {
        Board board = createBoard(4,
                EMPTY, EMPTY, EMPTY, X,
                EMPTY, EMPTY, EMPTY, X,
                EMPTY, EMPTY, EMPTY, X,
                EMPTY, EMPTY, EMPTY, X
        );

        assertHasWinner(board, true);
        assertWinnerIsX(board);
    }

    @Test
    public void winningDiagonalLeftRight() {
        Board board = createBoard(3,
                X,     EMPTY, EMPTY,
                EMPTY, X,     EMPTY,
                EMPTY, EMPTY, X
        );

        assertHasWinner(board, true);
        assertWinnerIsX(board);
    }


    @Test
    public void winningDiagonalRightLeftFourBoard() {
        Board board = createBoard(4,
                EMPTY, EMPTY, EMPTY, X,
                EMPTY, EMPTY, X,     EMPTY,
                EMPTY, X,     EMPTY, EMPTY,
                X,     EMPTY, EMPTY, EMPTY
        );

        assertHasWinner(board, true);
        assertWinnerIsX(board);
    }

    @Test
    public void gameIsOverWithAWinner() {
        Board board = createBoard(3,
                X,     X,     X,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY
        );

        assertThat(board.isFinished(), is(true));
    }

    @Test
    public void boardIsDrawn() {
        Board board = createBoard(3,
                X, O, X,
                X, O, X,
                O, X, O
        );

        assertHasWinner(board, false);
        assertThat(board.isDraw(), is(true));
    }

    private void assertWinnerIsX(Board board) {
        assertThat(board.getWinner().get(), is(Marker.X));
    }

    private void assertHasWinner(Board board, boolean is_winner) {
        assertThat(board.getWinner().isPresent(), is(is_winner));
    }
}
