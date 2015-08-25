package com.samhan;

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
  private final PrintStream output;

  public ConsoleDisplay(PrintStream output) {
    this.output = output;
  }

  @Override
  public void render(Board board, Marker marker) {
    clearDisplay();
    renderBoard(board);
  }

  private void clearDisplay() {
    output.print(ANSI_CLS + ANSI_HOME);
  }

  private void renderBoard(Board board) {
    displayBlankLine();
    List<String> positions = offsetPositions(board);
    List<String> centeredPositions = centeredPositions(positions);
    StringBuilder boardOutput = new StringBuilder();

    for (int rowIndex = 0; rowIndex < board.size(); rowIndex++) {
      List<String> rowPositions = groupedRowPositions(board.size(), centeredPositions, rowIndex);
      boardOutput.append(StringUtils.join(rowPositions, "|"));
      boardOutput.append(rowSeparator(board.size()));
    }

    output.print(boardOutput);
    displayBlankLine();
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
    for (int i = 0; i < board.size() * board.size(); i++) {
      if (board.isAvailable(i)) {
        positions.add(Integer.toString(i + OFFSET));
      } else {
        positions.add(board.getMarkerAt(i).toString());
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
    String singleCellRowSeparator = StringUtils.repeat("-", CELL_WIDTH);
    rowSeparator += StringUtils.repeat(singleCellRowSeparator, "+", boardSize);
    return rowSeparator + "\n";
  }

  private void displayBlankLine() {
    output.print("\n");
  }
}
