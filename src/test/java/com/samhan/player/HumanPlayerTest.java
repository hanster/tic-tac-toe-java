package com.samhan.player;


import com.samhan.Board;
import com.samhan.Fakes.PlayerInputOutputSpy;
import com.samhan.Marker;
import com.samhan.ui.PlayerInputOutput;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HumanPlayerTest {
  @Test
  public void hasAMarker() {
    PlayerInputOutput input = new PlayerInputOutputSpy();
    Player humanPlayer = new HumanPlayer(Marker.X, input);

    assertThat(humanPlayer.getMarker(), is(Marker.X));
  }

  @Test
  public void usesInputToGetTheMove() {
    PlayerInputOutputSpy input = new PlayerInputOutputSpy();
    Player humanPlayer = new HumanPlayer(Marker.X, input);

    humanPlayer.nextMove(new Board());

    assertThat(input.getMoveTimesCalled, is(1));
  }

}
