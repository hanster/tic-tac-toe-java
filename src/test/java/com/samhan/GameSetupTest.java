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
    PlayerType player1 = PlayerType.HUMAN;
    PlayerType player2 = PlayerType.HUMAN;
    BoardType board = BoardType.THREE;
    gameSetup.buildGame(player1, player2, board);
  }
}