package com.samhan;

import com.samhan.fakes.DisplaySpy;
import com.samhan.fakes.PlayerStub;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static com.samhan.BoardCreationHelper.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {
    private PlayerStub player1;
    private PlayerStub player2;
    private DisplaySpy display;

    @Before
    public void setUp() {
        player1 = new PlayerStub(Marker.X, new LinkedList<>(Arrays.asList(1, 3, 4, 6, 8)));
        player2 = new PlayerStub(Marker.O, new LinkedList<>(Arrays.asList(2, 5, 7, 9)));
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
        Board board = createBoard(3,
                O, O, O,
                O, O, O,
                O, O, O
        );
        GameParams params = new GameParams(player1, player2, board, display);


        Game game = new Game(params);

        assertThat(game.isRunning(), is(false));
    }

    @Test
    public void gameRenderingDelegatesToDisplay() {
        GameParams params = new GameParams(player1, player2, new Board(), display);
        Game game = new Game(params);

        game.render();

        assertThat(display.getRenderTimesCalled(), is(1));
    }

    @Test
    public void playTurnGetsMoveFromPlayer() {
        GameParams params = new GameParams(player1, player2, new Board(), display);
        Game game = new Game(params);

        game.playTurn();

        assertThat(player1.getNextMoveTimesCalled(), is(1));
    }

    @Test
    public void playTurnSwitchesCorrectly() {
        GameParams params = new GameParams(player1, player2, new Board(), display);
        Game game = new Game(params);

        game.playTurn();
        game.playTurn();

        assertThat(player1.getNextMoveTimesCalled(), is(1));
        assertThat(player2.getNextMoveTimesCalled(), is(1));
    }

    @Test
    public void testFullGamePlays() {
        PlayerStub player1 = new PlayerStub(Marker.X, new LinkedList<>(Arrays.asList(1, 3, 4, 6, 8)));
        PlayerStub player2 = new PlayerStub(Marker.O, new LinkedList<>(Arrays.asList(2, 5, 7, 9)));
        GameParams params = new GameParams(player1, player2, new Board(), display);
        Game game = new Game(params);

        game.run();

        assertThat(display.getRenderTimesCalled(), is(10));
        assertThat(player1.getNextMoveTimesCalled(), is(5));
        assertThat(player2.getNextMoveTimesCalled(), is(4));
    }
}

