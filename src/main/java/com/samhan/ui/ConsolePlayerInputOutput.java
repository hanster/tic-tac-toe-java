package com.samhan.ui;

import com.samhan.Board;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsolePlayerInputOutput implements PlayerInputOuput {
  public static final String INVALID_ENTRY = "Invalid Entry (1-%s)";
  public static final String MOVE_ALREADY_TAKEN = "Move already taken";
  public static final int OFFSET = 1;
  public static final String ENTER_MOVE = "Enter move: ";
  private final PrintStream output;
  private final BufferedReader input;

  public ConsolePlayerInputOutput(InputStream inputStream, PrintStream output) {
    this.input = new BufferedReader(new InputStreamReader(inputStream));
    this.output = output;
  }

  @Override
  public int getMove(Board board) {
    while(true) {
      output.print(ENTER_MOVE);
      Result result = validateInput(board);
      if (result.errors.isEmpty()) {
        return result.value;
      } else {
        output.println(result.errors.toString());
      }
    }
  }

  private Result validateInput(Board board) {
    int move = -1;
    int boardMaxMove = board.size() * board.size();
    List<String> errorMessages = new ArrayList<>();
    try {
      move = tryToGetValidIntegerInput();
      validateBoundary(move, boardMaxMove, errorMessages);
      move = move - OFFSET;
      validateMoveAvailable(board, move, errorMessages);
    } catch (IOException | NumberFormatException e) {
      errorMessages.add(String.format(INVALID_ENTRY, boardMaxMove));
    }
    return new Result(errorMessages, move);
  }

  private void validateMoveAvailable(Board board, int move, List<String> errorMessages) {
    if (errorMessages.isEmpty() && !board.isAvailable(move)) {
      errorMessages.add(MOVE_ALREADY_TAKEN);
    }
  }

  private void validateBoundary(int move, int boardMaxMove, List<String> errorMessages) {
    if (1 > move || move > boardMaxMove) {
      errorMessages.add(String.format(INVALID_ENTRY, boardMaxMove));
    }
  }

  private int tryToGetValidIntegerInput() throws IOException {
    return Integer.parseInt(input.readLine());
  }

  private class Result {
    public final List<String> errors;
    public final int value;

    public Result(List<String> errors, int value) {
      this.errors = errors;
      this.value = value;
    }
  }
}
