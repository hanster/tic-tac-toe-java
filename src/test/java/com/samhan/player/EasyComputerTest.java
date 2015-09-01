package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
}
