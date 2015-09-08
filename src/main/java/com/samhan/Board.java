package com.samhan;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

public class Board {
    private static final int OFFSET = 1;
    private final List<Optional<Marker>> marks;
    private final int boardSize;

    public Board() {
        this(3);
    }

    public Board(int size) {
        this.boardSize = size;
        this.marks = new LinkedList<>(Collections.nCopies(size * size, Optional.<Marker>empty()));
    }

    public Board(int size, List<Optional<Marker>>marks) {
        this.boardSize = size;
        this.marks = marks;
    }

    public int size() {
        return boardSize;
    }

    public boolean isEmpty() {
        return marks.stream().allMatch(marker -> !marker.isPresent());
    }

    public Optional<Marker> getMarkerAt(int position) {
        return marks.get(position - OFFSET);
    }

    public Board placeAt(int position, Marker marker) {
        List<Optional<Marker>> newBoardMarks = new LinkedList<>(marks);
        newBoardMarks.set(position - OFFSET, Optional.of(marker));
        return new Board(boardSize, newBoardMarks);
    }

    public boolean isAvailable(int position) {
        return !marks.get(position - OFFSET).isPresent();
    }

    public List<Integer> availableMoves() {
        return range(1, marks.size() + 1)
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
        return getWinningLine().map(Line::firstMark).orElse(Optional.empty());
    }

    private Optional<Line> getWinningLine() {
        return allLines()
                .stream()
                .filter(Line::isWinner)
                .findFirst();
    }

    public int movesMade() {
        return marks.size() - availableMoves().size();
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
        return new Line(marks.subList(rowIndex * boardSize, rowIndex * boardSize + boardSize));
    }

    private List<Line> allColumnLines() {
        return range(0, boardSize)
                .mapToObj(this::columnLine)
                .collect(Collectors.toList());
    }

    private Line columnLine(int columnIndex) {
        List<Optional<Marker>> columnMarkers = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            columnMarkers.add(marks.get(boardSize * i + columnIndex));
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
        List<Optional<Marker>> leftRightMarkers = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            leftRightMarkers.add(marks.get(i + i * boardSize));
        }
        return new Line(leftRightMarkers);
    }

    private Line rightLeftDiagonalLine() {
        List<Optional<Marker>> rightLeftMarkers = new ArrayList<>();
        for (int i = 1; i <= boardSize; i++) {
            rightLeftMarkers.add(marks.get(i * boardSize - i));
        }
        return new Line(rightLeftMarkers);
    }

    private class Line {
        private final List<Optional<Marker>> marks;

        public Line(List<Optional<Marker>> marks) {
            this.marks = marks;
        }

        public boolean isWinner() {
            return firstMarkNotEmpty() && allTheSame();
        }

        public Optional<Marker> firstMark() {
            return marks.get(0);
        }

        private boolean firstMarkNotEmpty() {
            return marks.get(0).isPresent();
        }

        private boolean allTheSame() {
            return marks.stream().allMatch(mark -> mark.equals(marks.get(0)));
        }
    }
}