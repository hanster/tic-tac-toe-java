package com.samhan.ui;

import com.samhan.Board;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.samhan.BoardCreationHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConsolePlayerInputOutputTest {
    private ByteArrayOutputStream output;
    private ConsolePlayerInputOutput consoleIO;

    private void setUpQueuedConsoleInput(String[] listOfInputs) {
        String totalInputs = "";
        for (String input : listOfInputs) {
            totalInputs = totalInputs + input + "\n";
        }
        output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        UserInput userInput = new UserInput(new ByteArrayInputStream(totalInputs.getBytes()));
        consoleIO = new ConsolePlayerInputOutput(userInput, printStream);
    }

    @Test
    public void displayEnterMove() {
        setUpQueuedConsoleInput(new String[]{"5"});
        consoleIO.getMove(new Board());

        assertThat(output.toString(), containsString("Enter move:"));
    }

    @Test
    public void getValidInput() {
        setUpQueuedConsoleInput(new String[]{"5"});

        assertThat(consoleIO.getMove(new Board()), is(5));
    }

    @Test
    public void promptAgainWhenNonNumberInput() {
        setUpQueuedConsoleInput(new String[]{"a", "5"});

        assertThat(consoleIO.getMove(new Board()), is(5));
    }

    @Test
    public void displayInvalidEntry() {
        setUpQueuedConsoleInput(new String[]{"a", "5"});

        consoleIO.getMove(new Board());

        assertThat(output.toString(), containsString("Invalid Entry (1-9)"));
    }

    @Test
    public void displayInvalidEntryFourBoard() {
        setUpQueuedConsoleInput(new String[]{"a", "5"});

        consoleIO.getMove(new Board(4));

        assertThat(output.toString(), containsString("Invalid Entry (1-16)"));
    }

    @Test
    public void displayMoveAlreadyTaken() {
        setUpQueuedConsoleInput(new String[]{"5", "6"});
        Board board = createBoard(3,
                EMPTY, EMPTY, EMPTY,
                EMPTY, O,     EMPTY,
                EMPTY, EMPTY, EMPTY
        );

        consoleIO.getMove(board);

        assertThat(output.toString(), containsString("Invalid Entry (1-9)"));
    }

    @Test
    public void displayInvalidEntryWhenMoveOutOfRange() {
        setUpQueuedConsoleInput(new String[]{"0", "6"});
        Board board = createBoard(3,
                EMPTY, EMPTY, EMPTY,
                EMPTY, O,     EMPTY,
                EMPTY, EMPTY, EMPTY
        );

        consoleIO.getMove(board);

        assertThat(output.toString(), containsString("Invalid Entry (1-9"));
    }
}
