package com.samhan.ui;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
        UserInput userInput = new UserInput(new ByteArrayInputStream(totalInputs.getBytes()));
        console = new ConsolePlayAgainInput(userInput, printStream);
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
}
