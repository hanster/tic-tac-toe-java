package com.samhan;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {
  private FakePlayer player1;
  private FakePlayer player2;
  private DisplaySpy display;

  @Before
  public void setUp() {
    player1 = new FakePlayer(Marker.X);
    player2 = new FakePlayer(Marker.O);
    display = new DisplaySpy();
  }

  @Test
  public void newGameIsRunning() {
    Game game = new Game(player1, player2, new Board(), display);

    assertThat(game.isRunning(), is(true));
  }

  @Test
  public void gameIsNotRunningIfBoardIsFinished() {
    Board board = new Board(3, new Marker[] {
            Marker.O, Marker.O, Marker.O,
            Marker.O, Marker.O, Marker.O,
            Marker.O, Marker.O, Marker.O
    });
    Game game = new Game(player1, player2, board, display);

    assertThat(game.isRunning(), is(false));
  }

  @Test
  public void gameRenderingDelegatesToDisplay() {
    Game game = new Game(player1, player2, new Board(), display);

    game.render();

    assertThat(display.renderTimesCalled, is(1));
  }

  @Test
  public void playTurnGetsMoveFromPlayer() {
    Game game = new Game(player1, player2, new Board(), display);

    game.playTurn();

    assertThat(player1.nextMoveTimesCalled, is(1));
  }

  @Test
  public void playTurnSwitchesCorrectly() {
    Game game = new Game(player1, player2, new Board(), display);

    game.playTurn();
    game.playTurn();

    assertThat(player1.nextMoveTimesCalled, is(1));
    assertThat(player2.nextMoveTimesCalled, is(1));
  }

  private class FakePlayer implements Player {

    private final Marker marker;
    public int nextMoveTimesCalled;

    public FakePlayer(Marker marker) {
      this.nextMoveTimesCalled = 0;
      this.marker = marker;
    }

    @Override
    public Marker getMarker() {
      return marker;
    }

    @Override
    public int nextMove(Board board) {
      nextMoveTimesCalled++;
      return 0;
    }
  }

  private class DisplaySpy implements Display {
    public int renderTimesCalled;

    public DisplaySpy() {
      renderTimesCalled = 0;
    }

    @Override
    public void render(Board board, Marker marker) {
      renderTimesCalled++;
    }
  }
}

