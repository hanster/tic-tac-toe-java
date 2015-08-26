package com.samhan;

import com.samhan.Fakes.DisplaySpy;
import com.samhan.Fakes.PlayerInputOutputSpy;
import com.samhan.player.EasyComputer;
import com.samhan.player.HardComputer;
import com.samhan.player.Human;
import com.samhan.ui.Display;
import com.samhan.ui.PlayerInputOutput;
import com.samhan.ui.PlayerSelection;
import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class GameSetupTest {
  @Test
  public void getParamsForAGame() {
    Display display = new DisplaySpy();
    PlayerInputOutput playerInputOutput = new PlayerInputOutputSpy();
    LinkedList<PlayerType> playerTypes = new LinkedList<>();
    playerTypes.add(PlayerType.HUMAN);
    playerTypes.add(PlayerType.EASY_COMPUTER);
    PlayerSelectionStub playerSelection = new PlayerSelectionStub(playerTypes);
    GameSetup gameSetup = new GameSetup(display, playerInputOutput, playerSelection);

    GameParams params = gameSetup.buildGame(BoardType.THREE);

    assertThat(params.player1, instanceOf(Human.class));
    assertThat(params.player2, instanceOf(EasyComputer.class));
    assertThat(params.board.size(), is(3));
  }

  @Test
  public void getParamsForHardComputerAndFourBoard() {
    Display display = new DisplaySpy();
    PlayerInputOutput playerInputOutput = new PlayerInputOutputSpy();
    LinkedList<PlayerType> playerTypes = new LinkedList<>();
    playerTypes.add(PlayerType.HUMAN);
    playerTypes.add(PlayerType.HARD_COMPUTER);
    PlayerSelectionStub playerSelection = new PlayerSelectionStub(playerTypes);
    GameSetup gameSetup = new GameSetup(display, playerInputOutput, playerSelection);

    GameParams params = gameSetup.buildGame(BoardType.FOUR);

    assertThat(params.player1, instanceOf(Human.class));
    assertThat(params.player2, instanceOf(HardComputer.class));
    assertThat(params.board.size(), is(4));
  }

  public class PlayerSelectionStub implements PlayerSelection {
    private final LinkedList<PlayerType> playerTypes;

    public PlayerSelectionStub(LinkedList<PlayerType> playerTypes) {
      this.playerTypes = playerTypes;
    }

    @Override
    public PlayerType select(String playerNumber) {
      return playerTypes.remove();
    }
  }
}