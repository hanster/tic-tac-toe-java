package com.samhan.ui;

import com.samhan.Board;
import com.samhan.Marker;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.StringUtils.*;

public class ConsoleDisplay implements Display {
    private static final String ANSI_CLS = "\u001b[2J";
    private static final String ANSI_HOME = "\u001b[H";
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
        output.println(format(CURRENT_PLAYER, marker.toString()));
    }

    private void renderEndStatus(Board board) {
        output.println(endStatusMessage(board));
    }

    private String endStatusMessage(Board board) {
        return format(GAME_OVER_RESULT, getResult(board));
    }

    private String getResult(Board board) {
        return board.getWinner()
                .map(winner -> winner.toString() + WINS)
                .orElse(DRAW);
    }

    private void clearDisplay() {
        output.print(ANSI_CLS + ANSI_HOME);
    }

    private void renderBoard(Board board) {
        displayBlankLine();
        displayBoard(board);
        displayBlankLine();
    }

    private void displayBoard(Board board) {
        List<String> cells = formatCells(board);
        List<String> boardRows = splitIntoRows(board, cells);
        String formattedBoard = join(boardRows, rowSeparator(board.size()));
        output.println(formattedBoard);
    }

    private List<String> splitIntoRows(Board board, List<String> centeredPositions) {
        return range(0, board.size())
                .mapToObj(i -> rowLine(centeredPositions, board.size(), i))
                .map(rowLine -> join(rowLine, CELL_SEPARATOR))
                .collect(Collectors.toList());
    }

    private List<String> rowLine(List<String> allCells, int boardSize, int rowIndex) {
        return allCells.subList(rowIndex * boardSize, rowIndex * boardSize + boardSize);
    }

    private List<String> formatCells(Board board) {
        return range(1, board.size() * board.size() + 1)
                .mapToObj(i -> cellRepresentation(board, i))
                .map(this::centerCell)
                .collect(Collectors.toList());
    }

    private String cellRepresentation(Board board, int cellNumber) {
        return board.getMarkerAt(cellNumber).map(Enum::toString).orElse("" + cellNumber);
    }

    private String centerCell(String cell) {
        return center(cell, CELL_WIDTH);
    }

    private String rowSeparator(int boardSize) {
        String rowSeparator = "\n";
        String singleCellRowSeparator = repeat(ROW_CELL_SEPARATOR, CELL_WIDTH);
        rowSeparator += repeat(singleCellRowSeparator, CROSS_SEPARATOR, boardSize);
        return rowSeparator + "\n";
    }

    private void displayBlankLine() {
        output.print("\n");
    }
}
