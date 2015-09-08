package com.samhan;

import com.samhan.ui.*;
import com.samhan.ui.console.ConsoleDisplay;
import com.samhan.ui.console.ConsoleOptionMenu;
import com.samhan.ui.console.ConsolePlayerInput;

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
        ConsoleOptionMenu optionMenu = new ConsoleOptionMenu(userInput, output);
        ConsoleDisplay display = new ConsoleDisplay(output);
        ConsolePlayerInput playerInput = new ConsolePlayerInput(userInput, display);

        GameSetup gameSetup = new GameSetup(display, playerInput, optionMenu);

        do {
            display.greet();
            GameParams gameParams = gameSetup.buildGame();
            Game game = new Game(gameParams);

            game.run();
        } while (optionMenu.doPlayAgain());
        display.farewell();
    }
}
