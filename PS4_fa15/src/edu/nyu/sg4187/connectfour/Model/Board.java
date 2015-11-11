package edu.nyu.sg4187.connectfour.Model;

import java.util.ArrayList;
import java.util.List;

public class Board {
  private int maxRows = 6;
  private int maxColumns = 7;
  private int[] columnState;
  private int[][] location;
  private int nextPlayer;
  private int currentMoveX = 0;
  private int currentMoveY = 0;
  private int winner;
  private static Board board = new Board();

  /**
   * Inner Class that saves the point as Coordinate.
   * 
   */
  public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }
  }

  private List<Coordinate> historyOfMoves;
  private List<Coordinate> winningSteps;

  private Board() {
  }

  /**
   * Singleton Board Instance
   * 
   * @return board Instance.
   */
  public static Board getInstance(int maxRow, int maxCol) {
    if (board == null) {
      board = new Board();
    }
    board.reset(maxRow, maxCol);
    return board;
  }

  /**
   * Reset the Board
   * 
   * @param maxRow
   *          new maximum rows.
   * @param maxCol
   *          new maximum columns.
   */
  public void reset(int maxRow, int maxCol) {
    board.maxRows = maxRow;
    board.maxColumns = maxCol;
    historyOfMoves = new ArrayList<Coordinate>();
    winningSteps = new ArrayList<Coordinate>();
    location = new int[maxRows][maxColumns];
    columnState = new int[maxColumns];
    winner = -1;
    nextPlayer = 1;
  }

  /**
   * @param col
   *          is the column caller want to query.
   * @return current load of column "col"
   */
  public int getColumnState(int col) {
    return columnState[col];
  }

  /**
   * 
   * @param col
   *          the column that one step want to undo.
   * @return true, if can undo one step.
   */
  public boolean undoMove() {
    if (historyOfMoves.size() > 0) {
      int x = this.maxRows - board.getColumnState(this.currentMoveY);
      int y = this.currentMoveY;
      if (this.location[x][y] == (3 - nextPlayer)) {
        this.location[x][y] = 0;
        this.columnState[y]--;
        this.nextPlayer = 3 - this.nextPlayer;
        int lastStepIndex = historyOfMoves.size() - 1;
        this.currentMoveX = historyOfMoves.get(lastStepIndex).x;
        this.currentMoveY = historyOfMoves.get(lastStepIndex).y;
        this.winner = 0;
        historyOfMoves.remove(lastStepIndex);
        return true;
      }
    }
    return false;
  }

  /**
   * Fill the (pos) column in the board.
   * 
   * @param pos
   *          is the column that caller want to fill.
   * 
   */
  public void move(int pos) {
    if ((pos < 0) || (pos >= this.maxColumns)) {
      throw new RuntimeException("Invalid Input");
    }
    if ((this.columnState[pos] >= this.maxRows)) {
      throw new RuntimeException("Sorry, Column you selected is full. Please select a valid column");
    }

    this.historyOfMoves.add(new Coordinate(currentMoveX, currentMoveY));
    this.currentMoveY = pos;
    this.currentMoveX = this.maxRows - 1 - this.columnState[pos];
    this.columnState[pos]++;
    this.location[currentMoveX][currentMoveY] = nextPlayer;
    this.nextPlayer = 3 - this.nextPlayer;
  }

  /**
   * Getter for X-axis of the current step.
   * 
   * @return moveX
   */
  public int getMoveX() {
    return currentMoveX;
  }

  /**
   * Getter for Y-axis of the current step.
   * 
   * @return moveY
   */
  public int getMoveY() {
    return currentMoveY;
  }

  /**
   * @return the whole board's data.
   */
  public int[][] getBoardEntries() {
    return location;
  }

  /**
   * @return the nextPlayer.
   */
  public int nextPlayer() {
    return nextPlayer;
  }

  /**
   * @return the winner.
   */
  public int getWinnerNumber() {
    return winner;
  }

  private boolean isInside(int x, int y) {
    if (x >= maxRows || x < 0) {
      return false;
    }
    if (y >= maxColumns || y < 0) {
      return false;
    }
    return true;
  }

  private void stepTowards(int deltaX, int deltaY) {
    int currentPlayer = 3 - nextPlayer;
    int cellX = currentMoveX;
    int cellY = currentMoveY;
    while (isInside(cellX, cellY) && location[cellX][cellY] == currentPlayer) {
      Coordinate currentStep = new Coordinate(cellX, cellY);
      currentStep.x = cellX;
      currentStep.y = cellY;
      winningSteps.add(currentStep);
      cellX += deltaX;
      cellY += deltaY;
    }
  }

  private boolean isFourDotsInLine() {
    if (winningSteps.size() >= 4) {
      winner = 3 - nextPlayer;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Judge whether there exists connected four.
   * 
   * @return true -- the game is over. false.
   */
  public boolean isOver() {

    boolean isBoardFull = true;
    for (int i = 0; i < maxColumns; i++) {
      if (location[0][i] == 0) {
        isBoardFull = false;
        break;
      }
    }
    if (isBoardFull) {
      return true;
    }

    // Search to bottom from current move
    winningSteps.clear();
    this.stepTowards(1, 0);
    if (isFourDotsInLine()) {
      return true;
    }

    // Search Horizontally
    winningSteps.clear();
    this.stepTowards(0, -1);
    if (!winningSteps.isEmpty()) {
      // So that current move is not counted twice
      winningSteps.remove(0);
    }
    this.stepTowards(0, 1);
    if (isFourDotsInLine()) {
      return true;
    }

    // Search Diagonal
    winningSteps.clear();
    this.stepTowards(1, 1);
    if (!winningSteps.isEmpty()) {
      // So that current move is not counted twice
      winningSteps.remove(0);
    }
    this.stepTowards(-1, -1);
    if (isFourDotsInLine()) {
      return true;
    }

    // Search other-diagonal
    winningSteps.clear();
    this.stepTowards(-1, 1);
    if (!winningSteps.isEmpty()) {
      // So that current move is not counted twice
      winningSteps.remove(0);
    }
    this.stepTowards(1, -1);
    if (isFourDotsInLine()) {
      return true;
    }
    return false;
  }

  /**
   * @return return the winning steps list.
   */
  public List<Coordinate> getWinningSteps() {
    return new ArrayList<Coordinate>(winningSteps);
  }

}
