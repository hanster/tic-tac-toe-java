package com.samhan.ui;

import com.samhan.Board;
import com.samhan.Marker;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    display.render(new Board(3), Marker.X);

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

    display.render(new Board(4), Marker.X);

    assertThat(output.toString(), containsString(emptyBoard));
  }

  @Test
  public void clearsTheScreen() {
    display.render(new Board(), Marker.X);

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
            Marker.EMPTY, Marker.EMPTY, Marker.EMPTY, Marker.X,
            Marker.EMPTY, Marker.EMPTY, Marker.X, Marker.EMPTY,
            Marker.EMPTY, Marker.X,     Marker.EMPTY, Marker.EMPTY,
            Marker.X,     Marker.EMPTY, Marker.EMPTY, Marker.EMPTY
    });

    display.render(board, Marker.X);

    assertThat(output.toString(), containsString(renderedBoard));
  }

  @Test
  public void displayTheNextPlayerTurn() {
    display.render(new Board(), Marker.X);

    assertThat(output.toString(), containsString("Current Player: X"));
  }

  @Test
  public void displayDrawEndStatus() {
    Board board = new Board(3, new Marker[]{
            Marker.X, Marker.O, Marker.X,
            Marker.X, Marker.O, Marker.X,
            Marker.O, Marker.X, Marker.O
    });

    display.render(board, Marker.X);

    assertThat(output.toString(), containsString("Game Over! Result: Draw"));
  }

  @Test
  public void displayXWinnerEndStatus() {
    Board board = new Board(3, new Marker[]{
            Marker.X, Marker.O, Marker.X,
            Marker.O, Marker.O, Marker.X,
            Marker.O, Marker.X, Marker.X
    });

    display.render(board, Marker.O);

    assertThat(output.toString(), containsString("Game Over! Result: X wins"));
  }


  @Test
  public void displayOWinnerEndStatus() {
    Board board = new Board(3, new Marker[]{
            Marker.X, Marker.O, Marker.X,
            Marker.O, Marker.O, Marker.O,
            Marker.O, Marker.X, Marker.X
    });

    display.render(board, Marker.X);

    assertThat(output.toString(), containsString("Game Over! Result: O wins"));
  }
}