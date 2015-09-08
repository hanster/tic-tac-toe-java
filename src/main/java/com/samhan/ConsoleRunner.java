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
        ConsolePlayerInput console = new ConsolePlayerInput(userInput, output);
        ConsoleOptionMenu optionMenu = new ConsoleOptionMenu(userInput, output);
        ConsoleDisplay display = new ConsoleDisplay(output);

        GameSetup gameSetup = new GameSetup(display, console, optionMenu);

        do {
            display.greet();
            GameParams gameParams = gameSetup.buildGame();
            Game game = new Game(gameParams);

            game.run();
        } while (optionMenu.doPlayAgain());
        display.farewell();
    }
}
