package edu.nyu.sg4187.connectfour.Model;

public class BoardBuilder {
  private int maxCol;
  private int maxRow;

  /**
   * Set the max column.
   * 
   * @param maxCol
   * @return the BoardBuilder.
   */
  public BoardBuilder withCol(int maxCol) {
    this.maxCol = maxCol;
    return this;
  }

  /**
   * Set the max row.
   * 
   * @param maxRow
   *          max row.
   * @return the BoardBuilder.
   */
  public BoardBuilder withRow(int maxRow) {
    this.maxRow = maxRow;
    return this;
  }

  /**
   * build the board by given row and column.
   * 
   * @return the Board type's board.
   */
  public Board build() {
    return Board.getInstance(maxRow, maxCol);
  }

}
