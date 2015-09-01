package com.samhan.ui;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class ConsoleGreeterTest {
    private ByteArrayOutputStream output;
    private ConsoleGreeter greeter;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        greeter = new ConsoleGreeter(printStream);
    }

    @Test
    public void clearsTheScreen() {
        greeter.greet();

        assertThat(output.toString(), startsWith("\u001B[2J\u001B[H"));
    }

    @Test
    public void greetsWithAWelcomeMessage() {
        greeter.greet();

        assertThat(output.toString(), containsString("Welcome to TicTcToe\n\nTime to set up!\n\n"));
    }

    @Test
    public void farewellMessage() {
        greeter.farewell();

        assertThat(output.toString(), containsString("Thanks for playing!\n\nBye!"));

    }
}
