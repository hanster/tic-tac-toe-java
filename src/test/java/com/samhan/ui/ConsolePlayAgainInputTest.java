package com.samhan.ui;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class ConsolePlayAgainInputTest {
    private ByteArrayOutputStream output;
    private ConsolePlayAgainInput console;

    private void setUpQueuedConsoleInput(String[] listOfInputs) {
        String totalInputs = "";
        for (String input : listOfInputs) {
            totalInputs = totalInputs + input + "\n";
        }
        output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        console = new ConsolePlayAgainInput(new ByteArrayInputStream(totalInputs.getBytes()), printStream);
    }

    @Test
    public void displaysQuestion() {
        setUpQueuedConsoleInput(new String[]{"Y"});

        console.doPlayAgain();

        assertThat(output.toString(), containsString("Do you want to play again?"));
    }

    @Test
    public void yReturnsTrue() {
        setUpQueuedConsoleInput(new String[]{"Y"});

        assertThat(console.doPlayAgain(), is(true));
    }

    @Test
    public void yesReturnsTrue() {
        setUpQueuedConsoleInput(new String[]{"Yes"});

        assertThat(console.doPlayAgain(), is(true));
    }

    @Test
    public void nReturnsFalse() {
        setUpQueuedConsoleInput(new String[]{"N"});

        assertThat(console.doPlayAgain(), is(false));
    }

    @Test
    public void noReturnsFalse() {
        setUpQueuedConsoleInput(new String[]{"No"});

        assertThat(console.doPlayAgain(), is(false));
    }

    @Test
    public void promptsUntilValid() {
        setUpQueuedConsoleInput(new String[]{"Nope", "O", "n"});

        assertThat(console.doPlayAgain(), is(false));
        assertThat(output.toString(), containsString("Invalid Entry. [Y]es or [N]o"));
    }

    @Test
    public void clearsTheScreen() {
        setUpQueuedConsoleInput(new String[]{"Y"});

        console.greet();

        assertThat(output.toString(), startsWith("\u001B[2J\u001B[H"));
    }

    @Test
    public void greetsWithAWelcomeMessage() {
        setUpQueuedConsoleInput(new String[]{"Y"});

        console.greet();

        assertThat(output.toString(), containsString("Welcome to TicTcToe\n\nTime to set up!\n\n"));
    }

    @Test
    public void farewellMessage() {
        setUpQueuedConsoleInput(new String[]{"Y"});

        console.farewell();

        assertThat(output.toString(), containsString("Thanks for playing!\n\nBye!"));

    }
}
