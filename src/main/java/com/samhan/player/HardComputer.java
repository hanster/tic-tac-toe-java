package com.samhan.player;

import com.samhan.Board;
import com.samhan.Marker;

import java.util.Random;

import static java.lang.Math.max;

public class HardComputer implements Player {
    private static final int START_ALPHA = -100;
    private static final int START_BETA = 100;
    private static final int DEPTH_LIMIT = 7;
    private static final int NEUTRAL_SCORE = 0;
    private static final int WIN_SCORE = 100;
    public static final int MOVE_LIMIT = 11;
    private final Marker marker;

    public HardComputer(Marker marker) {
        this.marker = marker;
    }

    @Override
    public Marker getMarker() {
        return marker;
    }

    @Override
    public int nextMove(Board board) {
        if (board.availableMoves().size() <= MOVE_LIMIT) {
            ScoredMove scoredMove = negamax(board, START_ALPHA, START_BETA, DEPTH_LIMIT, marker);
            return scoredMove.move;
        } else {
            return randomMove(board);
        }
    }

    private int randomMove(Board board) {
        int idx = new Random().nextInt(board.availableMoves().size());
        return board.availableMoves().get(idx);
    }

    private ScoredMove negamax(Board board, int alpha, int beta, int depth, Marker marker) {
        ScoredMove bestScoredMove = new ScoredMove(-1, -101);
        if (canEvaluate(depth, board)) {
            return evaluatedMove(board, marker);
        }
        for (Integer move : board.availableMoves()) {
            Board nextBoard = board.placeAt(move, marker);
            int nodeValue = -negamax(nextBoard, -beta, -alpha, depth - 1, marker.opponent()).score;
            if (nodeValue > bestScoredMove.score) {
                bestScoredMove.score = nodeValue;
                bestScoredMove.move = move;
            }
            alpha = max(alpha, nodeValue);
            if (alpha > beta) {
                break;
            }
        }
        return bestScoredMove;
    }

    private boolean canEvaluate(int depth, Board board) {
        return depth == 0 || board.isFinished();
    }

    private ScoredMove evaluatedMove(Board board, Marker marker) {
        return new ScoredMove(-1, nodeValue(board, marker));
    }

    private int nodeValue(Board board, Marker marker) {
        if (board.hasWinner()) {
            return scoreWinner(board.getWinner(), marker);
        } else {
            return NEUTRAL_SCORE;
        }
    }

    private int scoreWinner(Marker winner, Marker marker) {
        return winner == marker ? WIN_SCORE : -WIN_SCORE;
    }

    private class ScoredMove {
        public int move;
        public int score;

        public ScoredMove(int move, int score) {
            this.move = move;
            this.score = score;
        }
    }
}
