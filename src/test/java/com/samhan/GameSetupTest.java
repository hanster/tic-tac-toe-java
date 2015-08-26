package com.samhan;

import com.samhan.Fakes.DisplaySpy;
import com.samhan.Fakes.PlayerInputOutputSpy;
import com.samhan.player.EasyComputer;
import com.samhan.player.HardComputer;
import com.samhan.player.Human;
import com.samhan.ui.Display;
import com.samhan.ui.PlayerInputOutput;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class GameSetupTest {
  @Test
  public void getParamsForAGame() {
    Display display = new DisplaySpy();
    PlayerInputOutput playerInputOutput = new PlayerInputOutputSpy();
    GameSetup gameSetup = new GameSetup(display, playerInputOutput);
    GameParams params = gameSetup.buildGame(PlayerType.HUMAN, PlayerType.EASY_COMPUTER, BoardType.THREE);

    assertThat(params.player1, instanceOf(Human.class));
    assertThat(params.player2, instanceOf(EasyComputer.class));
    assertThat(params.board.size(), is(3));
  }

  @Test
  public void getParamsForHardComputerAndFourBoard() {
    Display display = new DisplaySpy();
    PlayerInputOutput playerInputOutput = new PlayerInputOutputSpy();
    GameSetup gameSetup = new GameSetup(display, playerInputOutput);
    GameParams params = gameSetup.buildGame(PlayerType.HUMAN, PlayerType.HARD_COMPUTER, BoardType.FOUR);

    assertThat(params.player1, instanceOf(Human.class));
    assertThat(params.player2, instanceOf(HardComputer.class));
    assertThat(params.board.size(), is(4));
  }
}