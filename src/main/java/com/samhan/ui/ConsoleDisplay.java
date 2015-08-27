package com.samhan.ui;

import com.samhan.Board;
import com.samhan.Marker;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleDisplay implements Display {
  private static final String ANSI_CLS = "\u001b[2J";
  private static final String ANSI_HOME = "\u001b[H";
  private static final int OFFSET = 1;
  private static final int CELL_WIDTH = 5;
  private static final String CURRENT_PLAYER = "Current Player: %s";
  private static final String CELL_SEPARATOR = "|";
  private static final String ROW_CELL_SEPARATOR = "-";
  private static final String CROSS_SEPARATOR = "+";
  private static final String GAME_OVER_RESULT = "Game Over! Result: %s";
  private static final String DRAW = "Draw";
  private static final String WINS = " wins";
  private final PrintStream output;

  public ConsoleDisplay(PrintStream output) {
    this.output = output;
  }

  @Override
  public void render(Board board, Marker marker) {
    clearDisplay();
    renderBoard(board);
    renderStatus(board, marker);
  }

  private void renderStatus(Board board, Marker marker) {
    displayBlankLine();
    if (!board.isFinished()) {
      renderOngoingStatus(marker);
    } else {
      renderEndStatus(board);
    }
    displayBlankLine();
  }

  private void renderOngoingStatus(Marker marker) {
    output.println(String.format(CURRENT_PLAYER, marker.toString()));
  }

  private void renderEndStatus(Board board) {
    if (board.hasWinner()) {
      Marker winner = board.getWinner();
      output.println(String.format(GAME_OVER_RESULT, winner.toString() + WINS));
    } else {
      output.println(String.format(GAME_OVER_RESULT, DRAW));
    }
  }

  private void clearDisplay() {
    output.print(ANSI_CLS + ANSI_HOME);
  }

  private void renderBoard(Board board) {
    displayBlankLine();
    List<String> positions = offsetPositions(board);
    List<String> centeredPositions = centeredPositions(positions);
    List<String> boardRows = splitIntoRows(board, centeredPositions);

    output.println(StringUtils.join(boardRows, rowSeparator(board.size())));
    displayBlankLine();
  }

  private List<String> splitIntoRows(Board board, List<String> centeredPositions) {
    List<String> boardRows = new ArrayList<>();

    for (int rowIndex = 0; rowIndex < board.size(); rowIndex++) {
      List<String> rowPositions = groupedRowPositions(board.size(), centeredPositions, rowIndex);
      boardRows.add(StringUtils.join(rowPositions, CELL_SEPARATOR));
    }
    return boardRows;
  }

  private List<String> groupedRowPositions(int boardSize, List<String> centeredPositions, int rowIndex) {
    List<String> rowPositions = new ArrayList<>();
    for (int j = 0; j < boardSize; j++) {
      rowPositions.add(centeredPositions.get(rowIndex * boardSize + j));
    }
    return rowPositions;
  }

  private List<String> offsetPositions(Board board) {
    List<String> positions = new ArrayList<>();
    for (int positionIndex = 0; positionIndex < board.size() * board.size(); positionIndex++) {
      if (board.isAvailable(positionIndex)) {
        positions.add(Integer.toString(positionIndex + OFFSET));
      } else {
        positions.add(board.getMarkerAt(positionIndex).toString());
      }
    }
    return positions;
  }

  private List<String> centeredPositions(List<String> positions) {
    return positions.stream()
            .map(boardMarker -> StringUtils.center(boardMarker, CELL_WIDTH))
            .collect(Collectors.toList());
  }

  private String rowSeparator(int boardSize) {
    String rowSeparator = "\n";
    String singleCellRowSeparator = StringUtils.repeat(ROW_CELL_SEPARATOR, CELL_WIDTH);
    rowSeparator += StringUtils.repeat(singleCellRowSeparator, CROSS_SEPARATOR, boardSize);
    return rowSeparator + "\n";
  }

  private void displayBlankLine() {
    output.print("\n");
  }
}
