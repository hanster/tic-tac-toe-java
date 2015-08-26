package com.samhan;

import com.samhan.Fakes.DisplaySpy;
import com.samhan.Fakes.PlayerInputOutputSpy;
import com.samhan.ui.Display;
import com.samhan.ui.PlayerInputOutput;
import org.junit.Test;

public class GameSetupTest {
  @Test
  public void buildsHumanVsHumanGame() {
    Display display = new DisplaySpy();
    PlayerInputOutput playerInputOutput = new PlayerInputOutputSpy();
    GameSetup gameSetup = new GameSetup(display, playerInputOutput);
    Game game = gameSetup.buildGame(PlayerType.HUMAN, PlayerType.HUMAN, BoardType.THREE);


  }

}