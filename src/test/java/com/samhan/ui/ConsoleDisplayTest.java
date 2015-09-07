package com.samhan.ui;

import com.samhan.Board;
import com.samhan.Marker;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.samhan.Marker.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class ConsoleDisplayTest {
    private ByteArrayOutputStream output;
    private ConsoleDisplay display;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        display = new ConsoleDisplay(printStream);
    }

    @Test
    public void displaysEmptyBoard() {
        String emptyBoard =
                "  1  |  2  |  3  \n" +
                        "-----+-----+-----\n" +
                        "  4  |  5  |  6  \n" +
                        "-----+-----+-----\n" +
                        "  7  |  8  |  9  \n";

        display.render(new Board(3), X);

        assertThat(output.toString(), containsString(emptyBoard));
    }


    @Test
    public void displaysEmptyFourBoard() {
        String emptyBoard =
                "\n" +
                        "  1  |  2  |  3  |  4  \n" +
                        "-----+-----+-----+-----\n" +
                        "  5  |  6  |  7  |  8  \n" +
                        "-----+-----+-----+-----\n" +
                        "  9  | 10  | 11  | 12  \n" +
                        "-----+-----+-----+-----\n" +
                        " 13  | 14  | 15  | 16  \n";

        display.render(new Board(4), X);

        assertThat(output.toString(), containsString(emptyBoard));
    }

    @Test
    public void clearsTheScreen() {
        display.render(new Board(), X);

        assertThat(output.toString(), startsWith("\u001B[2J\u001B[H"));
    }

    @Test
    public void displaysMarkers() {
        String renderedBoard =
                "\n" +
                        "  1  |  2  |  3  |  X  \n" +
                        "-----+-----+-----+-----\n" +
                        "  5  |  6  |  X  |  8  \n" +
                        "-----+-----+-----+-----\n" +
                        "  9  |  X  | 11  | 12  \n" +
                        "-----+-----+-----+-----\n" +
                        "  X  | 14  | 15  | 16  \n";
        Board board = new Board(4, new Marker[]{
                EMPTY, EMPTY, EMPTY, X,
                EMPTY, EMPTY, X,     EMPTY,
                EMPTY, X,     EMPTY, EMPTY,
                X,     EMPTY, EMPTY, EMPTY
        });

        display.render(board, X);

        assertThat(output.toString(), containsString(renderedBoard));
    }

    @Test
    public void displayTheNextPlayerTurn() {
        display.render(new Board(), X);

        assertThat(output.toString(), containsString("Current Player: X"));
    }

    @Test
    public void displayDrawEndStatus() {
        Board board = new Board(3, new Marker[]{
                X, O, X,
                X, O, X,
                O, X, O
        });

        display.render(board, X);

        assertThat(output.toString(), containsString("Game Over! Result: Draw"));
    }

    @Test
    public void displayXWinnerEndStatus() {
        Board board = new Board(3, new Marker[]{
                X, O, X,
                O, O, X,
                O, X, X
        });

        display.render(board, O);

        assertThat(output.toString(), containsString("Game Over! Result: X wins"));
    }


    @Test
    public void displayOWinnerEndStatus() {
        Board board = new Board(3, new Marker[]{
                X, O, X,
                O, O, O,
                O, X, X
        });

        display.render(board, X);

        assertThat(output.toString(), containsString("Game Over! Result: O wins"));
    }

    @Test
    public void statusHasABlankLineBeforeAndAfter() {
        display.render(new Board(), X);

        assertThat(output.toString(), containsString("\nCurrent Player: X\n\n"));
    }

    @Test
    public void boardHasBlankLineAfter() {
        String emptyBoard =
                "  1  |  2  |  3  \n" +
                        "-----+-----+-----\n" +
                        "  4  |  5  |  6  \n" +
                        "-----+-----+-----\n" +
                        "  7  |  8  |  9  \n\n";

        display.render(new Board(3), X);

        assertThat(output.toString(), containsString(emptyBoard));

    }
}