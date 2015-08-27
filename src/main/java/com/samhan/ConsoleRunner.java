package com.samhan;

import com.samhan.ui.*;

import java.io.InputStream;
import java.io.PrintStream;

public class ConsoleRunner {
  private final InputStream input;
  private final PrintStream output;

  public ConsoleRunner(InputStream input, PrintStream output) {
    this.input = input;
    this.output = output;
  }
  public void run() {
    ConsolePlayerInputOutput console = new ConsolePlayerInputOutput(input, output);
    ConsoleOptionMenu optionMenu = new ConsoleOptionMenu(input, output);
    ConsoleDisplay display = new ConsoleDisplay(output);
    ConsolePlayAgainInput asker = new ConsolePlayAgainInput(input, output);
    ConsoleGreeter greeter = new ConsoleGreeter(output);

    PlayerSelection playerSelector = new ConsolePlayerSelection(optionMenu);
    BoardSelection boardSelector = new ConsoleBoardSelection(optionMenu);
    GameSetup gameSetup = new GameSetup(display, console, playerSelector, boardSelector);

    do {
      greeter.greet();
      GameParams gameParams = gameSetup.buildGame();
      Game game = new Game(gameParams);

      game.run();
    } while (asker.doPlayAgain());
    greeter.farewell();
  }
}
