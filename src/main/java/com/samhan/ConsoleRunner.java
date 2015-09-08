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
        UserInput userInput = new UserInput(input);
        ConsolePlayerInputOutput console = new ConsolePlayerInputOutput(userInput, output);
        ConsoleOptionMenu optionMenu = new ConsoleOptionMenu(userInput, output);
        ConsoleDisplay display = new ConsoleDisplay(output);
        ConsolePlayAgainInput asker = new ConsolePlayAgainInput(userInput, output);

        PlayerSelection playerSelector = new ConsolePlayerSelection(optionMenu);
        BoardSelection boardSelector = new ConsoleBoardSelection(optionMenu);
        GameSetup gameSetup = new GameSetup(display, console, playerSelector, boardSelector);

        do {
            asker.greet();
            GameParams gameParams = gameSetup.buildGame();
            Game game = new Game(gameParams);

            game.run();
        } while (asker.doPlayAgain());
        asker.farewell();
    }
}
