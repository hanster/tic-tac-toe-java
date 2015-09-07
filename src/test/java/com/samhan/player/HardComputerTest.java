package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;
import org.junit.Test;

import java.util.List;

import static com.samhan.Marker.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HardComputerTest {
    @Test
    public void hasAMarker() {
        HardComputer computer = new HardComputer(O);

        assertThat(computer.getMarker(), is(O));
    }

    @Test
    public void makesAValidMove() {
        Board emptyBoard = new Board();
        HardComputer computer = new HardComputer(O);

        int computerMove = computer.nextMove(emptyBoard);

        assertThat(emptyBoard.isAvailable(computerMove), is(true));
    }

    @Test
    public void choosesOnlyAvailableMove() {
        Board board = new Board(3, new Marker[]{
                X, O, X,
                X, O, X,
                O, X, EMPTY
        });
        HardComputer computer = new HardComputer(O);

        int computerMove = computer.nextMove(board);

        assertThat(computerMove, is(9));
    }

    @Test
    public void chooseWinningMoveOutOf2Moves() {
        Board board = new Board(3, new Marker[]{
                X, O,     X,
                X, O,     X,
                O, EMPTY, EMPTY
        });
        HardComputer computer = new HardComputer(X);

        assertThat(computer.nextMove(board), is(9));
    }

    @Test
    public void choosesBlockingMove() {
        Board board = new Board(3, new Marker[]{
                X, O,     X,
                O, X,     X,
                O, EMPTY, EMPTY
        });
        HardComputer computer = new HardComputer(O);

        assertThat(computer.nextMove(board), is(9));
    }

    @Test
    public void testSlowMoves() {
        Board board = new Board(4, new Marker[]{
                X,     EMPTY, EMPTY, EMPTY,
                EMPTY, X,     EMPTY, EMPTY,
                EMPTY, EMPTY, X,     EMPTY,
                EMPTY, O,     O,     EMPTY,
        });
        HardComputer computer = new HardComputer(O);

        int computerMove = computer.nextMove(board);

        assertThat(computerMove, is(16));
    }

    @Test
    public void testsRandomMove() {
        Board board = new Board(4, new Marker[]{
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
        });
        HardComputer computer = new HardComputer(O);

        int computerMove = computer.nextMove(board);
        List<Integer> availableMoves = board.availableMoves();

        assertThat(availableMoves, hasItem(computerMove));
    }
}
