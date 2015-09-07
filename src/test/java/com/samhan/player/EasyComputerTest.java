package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class EasyComputerTest {
    @Test
    public void hasAMarker() {
        EasyComputer computer = new EasyComputer(Marker.O);

        assertThat(computer.getMarker(), is(Marker.O));
    }

    @Test
    public void choosesOnlyAvailableMove() {
        Board board = new Board(3, new Marker[]{
                Marker.X, Marker.O, Marker.X,
                Marker.X, Marker.O, Marker.X,
                Marker.O, Marker.X, Marker.EMPTY
        });
        EasyComputer computer = new EasyComputer(Marker.O);

        int computerMove = computer.nextMove(board);

        assertThat(computerMove, is(8));
    }

    @Test
    public void choosesFromOnlyAvailableMoves() {
        Board board = new Board(3);
        EasyComputer computer = new EasyComputer(Marker.O);
        List<Integer> availableMoves = board.availableMoves();

        int computerMove = computer.nextMove(board);
        int anotherMove = computer.nextMove(board);

        assertThat(availableMoves, hasItems(computerMove, anotherMove));
    }
}
