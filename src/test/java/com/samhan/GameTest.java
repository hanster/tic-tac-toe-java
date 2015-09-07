package com.samhan;

import com.samhan.Fakes.DisplaySpy;
import com.samhan.Fakes.PlayerStub;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {
    private PlayerStub player1;
    private PlayerStub player2;
    private DisplaySpy display;

    @Before
    public void setUp() {
        player1 = new PlayerStub(Marker.X, new LinkedList<>(Arrays.asList(0, 2, 3, 5, 7)));
        player2 = new PlayerStub(Marker.O, new LinkedList<>(Arrays.asList(1, 4, 6, 8)));
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
}

