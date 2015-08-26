package com.samhan.ui;

import com.samhan.BoardType;
import com.samhan.Fakes.OptionMenuStub;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConsoleBoardSelectionTest {
  @Test
  public void selectAThreeBoardType() {
    OptionMenu optionMenu = new OptionMenuStub(BoardType.THREE.getInput());
    BoardSelection selector = new ConsoleBoardSelection(optionMenu);

    BoardType boardType = selector.selectType();

    assertThat(boardType, is(BoardType.THREE));
  }

  @Test
  public void selectAtFourBoardType() {
    OptionMenu optionMenu = new OptionMenuStub(BoardType.FOUR.getInput());
    BoardSelection selector = new ConsoleBoardSelection(optionMenu);

    BoardType boardType = selector.selectType();

    assertThat(boardType, is(BoardType.FOUR));
  }

  @Test(expected = RuntimeException.class)
  public void unexpectedBoardType() {
    OptionMenu optionMenu = new OptionMenuStub("5");
    BoardSelection selector = new ConsoleBoardSelection(optionMenu);

    selector.selectType();
  }
}

