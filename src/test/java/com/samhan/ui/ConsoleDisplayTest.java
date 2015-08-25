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
}
