package com.samhan;

import com.samhan.ui.ConsoleDisplay;
import com.samhan.ui.ConsoleOptionMenu;
import com.samhan.ui.ConsolePlayerInputOutput;

public class TicTacToe {
  public static void main(String args[]) {
    ConsolePlayerInputOutput console = new ConsolePlayerInputOutput(System.in, System.out);
    ConsoleOptionMenu optionMenu = new ConsoleOptionMenu(System.in, System.out);
    ConsoleDisplay display = new ConsoleDisplay(System.out);

    String playerOneSelection = optionMenu.getSelection("Enter Player1 selection", PlayerType.options());
    String playerTwoSelection = optionMenu.getSelection("Enter Player2 selection", PlayerType.options());

    String boardSelection = optionMenu.getSelection("Enter Board size selection", BoardType.options());

    Game game = new GameSetup(display, console)
            .buildGame(PlayerType.getType(playerOneSelection),
                    PlayerType.getType(playerTwoSelection),
                    BoardType.getType(boardSelection));

    game.run();
  }
}
