package com.samhan.ui;

import com.samhan.Fakes.OptionMenuStub;
import com.samhan.PlayerType;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConsolePlayerSelectionTest {
  @Test
  public void selectAHumanPlayerType() {
    OptionMenu optionMenu = new OptionMenuStub(PlayerType.HUMAN.getInput());
    PlayerSelection selector = new ConsolePlayerSelection(optionMenu);

    PlayerType playerType = selector.select("1");

    assertThat(playerType, is(PlayerType.HUMAN));
  }

  @Test
  public void selectAnEasyComputerPlayerType() {
    OptionMenu optionMenu = new OptionMenuStub(PlayerType.EASY_COMPUTER.getInput());
    PlayerSelection selector = new ConsolePlayerSelection(optionMenu);

    PlayerType playerType = selector.select("1");

    assertThat(playerType, is(PlayerType.EASY_COMPUTER));
  }

  @Test
  public void selectAHardComputerPlayerType() {
    OptionMenu optionMenu = new OptionMenuStub(PlayerType.HARD_COMPUTER.getInput());
    PlayerSelection selector = new ConsolePlayerSelection(optionMenu);

    PlayerType playerType = selector.select("1");

    assertThat(playerType, is(PlayerType.HARD_COMPUTER));
  }

  @Test(expected = RuntimeException.class)
  public void unexpectedBoardType() {
    OptionMenu optionMenu = new OptionMenuStub("5");
    PlayerSelection selector = new ConsolePlayerSelection(optionMenu);

    selector.select("1");
  }
}
