package com.samhan;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

public class Board {
    public static final int OFFSET = 1;
    private final Marker[] marks;
    private final int boardSize;

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
        List<Marker> myMarks = new LinkedList<>(Arrays.asList(marks));

        return myMarks.stream().allMatch(marker -> marker.equals(Marker.EMPTY));
    }

    public Marker getMarkerAt(int position) {
        return marks[position - OFFSET];
    }

    public Board placeAt(int position, Marker marker) {
        Marker[] newBoardMarks = marks.clone();
        newBoardMarks[position - OFFSET] = marker;
        return new Board(boardSize, newBoardMarks);
    }

    public boolean isAvailable(int position) {
        return marks[position - OFFSET].equals(Marker.EMPTY);
    }

    public List<Integer> availableMoves() {
        return range(1, marks.length + 1)
                .filter(this::isAvailable)
                .boxed()
                .collect(Collectors.toList());
    }

    public boolean isFinished() {
        return hasNoMoreMoves() || getWinner().isPresent();
    }

    public boolean isDraw() {
        return hasNoMoreMoves() || !getWinner().isPresent();
    }

    public Optional<Marker> getWinner() {
        return getWinningLine().map(Line::firstMark);
    }

    private Optional<Line> getWinningLine() {
        return allLines()
                .stream()
                .filter(Line::isWinner)
                .findFirst();
    }

    public int movesMade() {
        return marks.length - availableMoves().size();
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
        return range(0, boardSize)
                .mapToObj(this::rowLine)
                .collect(Collectors.toList());
    }

    private Line rowLine(int rowIndex) {
        List<Marker> myMarks = new LinkedList<>(Arrays.asList(marks));

        return new Line(myMarks.subList(rowIndex * boardSize, rowIndex * boardSize + boardSize));
    }

    private List<Line> allColumnLines() {
        return range(0, boardSize)
                .mapToObj(this::columnLine)
                .collect(Collectors.toList());
    }

    private Line columnLine(int columnIndex) {
        List<Marker> columnMarkers = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            columnMarkers.add(marks[boardSize * i + columnIndex]);
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
            leftRightMarkers.add(marks[i + i * boardSize]);
        }
        return new Line(leftRightMarkers);
    }

    private Line rightLeftDiagonalLine() {
        List<Marker> rightLeftMarkers = new ArrayList<>();
        for (int i = 1; i <= boardSize; i++) {
            rightLeftMarkers.add(marks[i * boardSize - i]);
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