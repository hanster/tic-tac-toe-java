package com.samhan.ui;

import com.samhan.Board;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.StringUtils.*;

public class BoardPresenter {
    private final Board board;
    private static final int CELL_WIDTH = 5;
    private static final String CELL_SEPARATOR = "|";
    private static final String ROW_CELL_SEPARATOR = "-";
    private static final String CROSS_SEPARATOR = "+";

    public BoardPresenter(Board board) {
        this.board = board;
    }

    public String render() {
        List<String> cells = formatCells(board);
        List<String> boardRows = splitIntoRows(board, cells);
        return join(boardRows, rowSeparator(board.size()));
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
}
