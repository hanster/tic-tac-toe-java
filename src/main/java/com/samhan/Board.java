package com.samhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
  private Marker[] marks;
  private int boardSize;


  public Board() {
    this(3);
  }

  public Board(int size) {
    this.boardSize = size;
    this.marks = new Marker[size * size];
    Arrays.fill(marks, Marker.EMPTY);
  }

  public Board(int size, Marker[] marks) {
    this.boardSize = size;
    this.marks = marks;
  }

  public int size() {
    return boardSize;
  }

  public boolean isEmpty() {
    for (Marker mark : marks) {
      if (!mark.equals(Marker.EMPTY)) {
        return false;
      }
    }
    return true;
  }

  public Marker getMarkerAt(int position) {
    return marks[position];
  }

  public Board placeAt(int position, Marker marker) {
    Marker[] newBoardMarks = marks.clone();
    newBoardMarks[position] = marker;
    return new Board(boardSize, newBoardMarks);
  }

  public boolean isAvailable(int position) {
    return marks[position].equals(Marker.EMPTY);
  }

  public List<Integer> availableMoves() {
    List <Integer> freePositions = new ArrayList<>();
    for (int index = 0; index < marks.length; index++) {
      if (isAvailable(index)) {
        freePositions.add(index);
      }
    }

    return freePositions;
  }

  public boolean hasWinner() {
    return getWinner() != null;
  }

  public boolean isFinished() {
    return hasNoMoreMoves() || hasWinner();
  }

  public boolean isDraw() {
    return hasNoMoreMoves() || !hasWinner();
  }

  public Marker getWinner() {
    for (Line line : allLines()) {
      if (line.isWinner()) {
        return line.firstMark();
      }
    }
    return null;
  }

  private boolean hasNoMoreMoves() {
    return availableMoves().size() == 0;
  }

  private List<Line> allLines() {
    List<Line> allLines = new ArrayList<>();
    allLines.addAll(allRowLines());
    allLines.addAll(allColumnLines());
    allLines.addAll(allDiagonalLines());
    return allLines;
  }

  private List<Line> allRowLines() {
    List<Line> allRowLines = new ArrayList<>();
    for (int row = 0; row < boardSize; row++) {
      allRowLines.add(rowLine(row));
    }
    return allRowLines;
  }

  private Line rowLine(int rowIndex) {
    List<Marker> rowMarkers = new ArrayList<>();
    for (int i = 0; i < boardSize; i++) {
      rowMarkers.add(getMarkerAt(rowIndex * boardSize + i));
    }
    return new Line(rowMarkers);
  }

  private List<Line> allColumnLines() {
    List<Line> allColumnLines = new ArrayList<>();
    for (int column = 0; column < boardSize; column++) {
      allColumnLines.add(columnLine(column));
    }
    return allColumnLines;
  }

  private Line columnLine(int columnIndex) {
    List<Marker> columnMarkers = new ArrayList<>();
    for (int i = 0; i < boardSize; i++) {
      columnMarkers.add(getMarkerAt(boardSize * i + columnIndex));
    }
    return new Line(columnMarkers);
  }

  private List<Line> allDiagonalLines() {
    List<Line> allDiagonalLines = new ArrayList<>();
    allDiagonalLines.add(leftRightDiagonalLine());

    allDiagonalLines.add(rightLeftDiagonalLine());
    return allDiagonalLines;
  }

  private Line leftRightDiagonalLine() {
    List<Marker> leftRightMarkers = new ArrayList<>();
    for (int i = 0; i < boardSize; i++) {
      leftRightMarkers.add(getMarkerAt(i + i * boardSize));
    }
    return new Line(leftRightMarkers);
  }

  private Line rightLeftDiagonalLine() {
    List<Marker> rightLeftMarkers = new ArrayList<>();
    for (int i = 1; i <= boardSize; i++) {
      rightLeftMarkers.add(getMarkerAt(i * boardSize - i));
    }
    return new Line(rightLeftMarkers);
  }

  private class Line {
    private List<Marker> marks;

    public Line(List<Marker> marks) {
      this.marks = marks;
    }

    public boolean isWinner() {
      return firstMarkNotEmpty() && allTheSame();
    }

    public Marker firstMark() {
      return marks.get(0);
    }

    private boolean firstMarkNotEmpty() {
      return marks.get(0) != Marker.EMPTY;
    }

    private boolean allTheSame() {
      Marker firstMark = marks.get(0);
      for (Marker mark : marks) {
        if (mark != firstMark) {
          return false;
        }
      }
      return true;
    }
  }
}