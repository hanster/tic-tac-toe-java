package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.samhan.BoardCreationHelper.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class EasyComputerTest {
    private EasyComputer computer;

    @Before
    public void setUp() {
        computer = new EasyComputer(Marker.O);
    }
    @Test
    public void hasAMarker() {
        assertThat(computer.getMarker(), is(Marker.O));
    }

    @Test
    public void choosesOnlyAvailableMove() {
        Board board = createBoard(3,
                X, O, X,
                X, O, X,
                O, X, EMPTY
        );

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
