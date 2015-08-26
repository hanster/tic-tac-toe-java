package com.samhan;

import com.samhan.ui.*;

public class TicTacToe {
  public static void main(String args[]) {
    ConsolePlayerInputOutput console = new ConsolePlayerInputOutput(System.in, System.out);
    ConsoleOptionMenu optionMenu = new ConsoleOptionMenu(System.in, System.out);
    ConsoleDisplay display = new ConsoleDisplay(System.out);

    PlayerSelection playerSelector = new ConsolePlayerSelection(optionMenu);
    BoardSelection boardSelector = new ConsoleBoardSelection(optionMenu);
    GameParams gameParams = new GameSetup(display, console, playerSelector, boardSelector)
            .buildGame();
    Game game = new Game(gameParams);

    game.run();
  }
}
