package com.samhan;

import java.util.Random;

import static java.lang.Math.max;

public class HardComputerPlayer implements Player {
  private static final int START_ALPHA = -100;
  private static final int START_BETA = 100;
  private static final int DEPTH_LIMIT = 7;
  private static final int NEUTRAL_SCORE = 0;
  private static final int WIN_SCORE = 100;
  public static final int MOVE_LIMIT = 11;
  private final Marker marker;

  public HardComputerPlayer(Marker marker) {
    this.marker = marker;
  }

  @Override
  public Marker getMarker() {
    return marker;
  }

  @Override
  public int nextMove(Board board) {
    if (board.availableMoves().size() <= MOVE_LIMIT) {
      Move move = negamax(board, START_ALPHA, START_BETA, DEPTH_LIMIT, marker);
      return move.index;
    }
    else {
      return randomMove(board);
    }
  }

  private int randomMove(Board board) {
    int idx = new Random().nextInt(board.availableMoves().size());
    return board.availableMoves().get(idx);
  }

  private Move negamax(Board board, int alpha, int beta, int depth, Marker marker) {
    Move bestMove = new Move(-1, -101);
    if (canEvaluate(depth, board)) {
      return evaluatedMove(board, marker);
    }
    for (Integer moveIndex : board.availableMoves()) {
      Board nextBoard = board.placeAt(moveIndex, marker);
      int nodeValue = -negamax(nextBoard, -beta, -alpha, depth - 1, opponent(marker)).value;
      if (nodeValue > bestMove.value) {
        bestMove.value = nodeValue;
        bestMove.index = moveIndex;
      }
      alpha = max(alpha, nodeValue);
      if (alpha > beta) {
        break;
      }
    }
    return bestMove;
  }

  private Marker opponent(Marker marker) {
    return marker == Marker.X ? Marker.O : Marker.X;
  }

  private boolean canEvaluate(int depth, Board board) {
    return depth == 0 || board.isFinished();
  }

  private Move evaluatedMove(Board board, Marker marker) {
    return new Move(-1, nodeValue(board, marker));
  }

  private int nodeValue(Board board, Marker marker) {
    if (board.hasWinner()) {
      return scoreWinner(board.getWinner(), marker);
    }
    else {
      return NEUTRAL_SCORE;
    }
  }

  private int scoreWinner(Marker winner, Marker marker) {
    return winner == marker ? WIN_SCORE : -WIN_SCORE;
  }

  private class Move {
    public int index;
    public int value;

    public Move(int index, int value) {
      this.index = index;
      this.value = value;
    }
  }
}
