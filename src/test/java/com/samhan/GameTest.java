package com.samhan;

import com.samhan.Fakes.DisplaySpy;
import com.samhan.Fakes.PlayerSpy;
import org.junit.Before;
import org.junit.Test;

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
    Game game = new Game.Builder()
            .player1(player1)
            .player2(player2)
            .board(new Board())
            .display(display)
            .build();

    assertThat(game.isRunning(), is(true));
  }

  @Test
  public void gameIsNotRunningIfBoardIsFinished() {
    Board board = new Board(3, new Marker[] {
            Marker.O, Marker.O, Marker.O,
            Marker.O, Marker.O, Marker.O,
            Marker.O, Marker.O, Marker.O
    });

    Game game = new Game.Builder()
            .player1(player1)
            .player2(player2)
            .board(board)
            .display(display)
            .build();

    assertThat(game.isRunning(), is(false));
  }

  @Test
  public void gameRenderingDelegatesToDisplay() {
    Game game = new Game.Builder()
            .player1(player1)
            .player2(player2)
            .board(new Board())
            .display(display)
            .build();

    game.render();

    assertThat(display.renderTimesCalled, is(1));
  }

  @Test
  public void playTurnGetsMoveFromPlayer() {
    Game game = new Game.Builder()
            .player1(player1)
            .player2(player2)
            .board(new Board())
            .display(display)
            .build();

    game.playTurn();

    assertThat(player1.nextMoveTimesCalled, is(1));
  }

  @Test
  public void playTurnSwitchesCorrectly() {
    Game game = new Game.Builder()
            .player1(player1)
            .player2(player2)
            .board(new Board())
            .display(display)
            .build();

    game.playTurn();
    game.playTurn();

    assertThat(player1.nextMoveTimesCalled, is(1));
    assertThat(player2.nextMoveTimesCalled, is(1));
  }
}

