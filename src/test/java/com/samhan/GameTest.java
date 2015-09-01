package com.samhan;

import com.samhan.Fakes.DisplaySpy;
import com.samhan.Fakes.PlayerSpy;
import com.samhan.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {
    private PlayerSpy player1;
    private PlayerSpy player2;
    private DisplaySpy display;

    @Before
    public void setUp() {
        player1 = new PlayerSpy(Marker.X);
        player2 = new PlayerSpy(Marker.O);
        display = new DisplaySpy();
    }

    @Test
    public void newGameIsRunning() {
        GameParams params = new GameParams(player1, player2, new Board(), display);
        Game game = new Game(params);

        assertThat(game.isRunning(), is(true));
    }

    @Test
    public void gameIsNotRunningIfBoardIsFinished() {
        Board board = new Board(3, new Marker[]{
                Marker.O, Marker.O, Marker.O,
                Marker.O, Marker.O, Marker.O,
                Marker.O, Marker.O, Marker.O
        });
        GameParams params = new GameParams(player1, player2, board, display);


        Game game = new Game(params);

        assertThat(game.isRunning(), is(false));
    }

    @Test
    public void gameRenderingDelegatesToDisplay() {
        GameParams params = new GameParams(player1, player2, new Board(), display);
        Game game = new Game(params);

        game.render();

        assertThat(display.renderTimesCalled, is(1));
    }

    @Test
    public void playTurnGetsMoveFromPlayer() {
        GameParams params = new GameParams(player1, player2, new Board(), display);
        Game game = new Game(params);

        game.playTurn();

        assertThat(player1.nextMoveTimesCalled, is(1));
    }

    @Test
    public void playTurnSwitchesCorrectly() {
        GameParams params = new GameParams(player1, player2, new Board(), display);
        Game game = new Game(params);

        game.playTurn();
        game.playTurn();

        assertThat(player1.nextMoveTimesCalled, is(1));
        assertThat(player2.nextMoveTimesCalled, is(1));
    }

    @Test
    public void testFullGamePlays() {
        PlayerStub player1 = new PlayerStub(Marker.X, new LinkedList<>(Arrays.asList(0, 2, 3, 5, 7)));
        PlayerStub player2 = new PlayerStub(Marker.O, new LinkedList<>(Arrays.asList(1, 4, 6, 8)));
        GameParams params = new GameParams(player1, player2, new Board(), display);
        Game game = new Game(params);

        game.run();

        assertThat(display.renderTimesCalled, is(10));
        assertThat(player1.nextMoveTimesCalled, is(5));
        assertThat(player2.nextMoveTimesCalled, is(4));
    }

    private class PlayerStub implements Player {
        private final Marker marker;
        private final LinkedList<Integer> moves;
        public int nextMoveTimesCalled;

        public PlayerStub(Marker marker, LinkedList<Integer> moves) {
            this.marker = marker;
            this.moves = moves;
            this.nextMoveTimesCalled = 0;
        }

        @Override
        public Marker getMarker() {
            return marker;
        }

        @Override
        public int nextMove(Board board) {
            this.nextMoveTimesCalled++;
            return moves.remove();
        }
    }
}

