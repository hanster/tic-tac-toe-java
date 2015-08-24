package com.samhan;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HumanPlayerTest {
  @Test
  public void hasAMarker() {
    PlayerInputOuput input = new FakeUserInput();
    Player humanPlayer = new HumanPlayer(Marker.X, input);

    assertThat(humanPlayer.getMarker(), is(Marker.X));
  }

  @Test
  public void usesInputToGetTheMove() {
    FakeUserInput input = new FakeUserInput();
    Player humanPlayer = new HumanPlayer(Marker.X, input);

    humanPlayer.nextMove(new Board());

    assertThat(input.getMoveTimesCalled, is(1));
  }

  private class FakeUserInput implements PlayerInputOuput {
    public int getMoveTimesCalled;

    public FakeUserInput() {
      getMoveTimesCalled = 0;
    }
    @Override

    public int getMove(Board board) {
      getMoveTimesCalled++;
      return getMoveTimesCalled;
    }
  }
}
