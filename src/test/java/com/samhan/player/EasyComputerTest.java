package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.samhan.Marker.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class EasyComputerTest {
    private EasyComputer computer;

    @Before
    public void setUp() {
        computer = new EasyComputer(O);
    }
    @Test
    public void hasAMarker() {
        assertThat(computer.getMarker(), is(O));
    }

    @Test
    public void choosesOnlyAvailableMove() {
        Board board = new Board(3, new Marker[]{
                X, O, X,
                X, O, X,
                O, X, EMPTY
        });

        int computerMove = computer.nextMove(board);

        assertThat(computerMove, is(9));
    }

    @Test
    public void choosesFromOnlyAvailableMoves() {
        Board board = new Board(3);
        List<Integer> availableMoves = board.availableMoves();

        int computerMove = computer.nextMove(board);
        int anotherMove = computer.nextMove(board);

        assertThat(availableMoves, hasItems(computerMove, anotherMove));
    }
}
