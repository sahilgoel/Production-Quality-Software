package edu.nyu.sg4187.connectfour.Model;

public class HumanPlayer implements IPlayer {
  private Board board;
  private int move = -1;

  /**
   * protected. Using for PlayerFactory Only.
   * 
   * @param board
   *          input is board object.
   */
  HumanPlayer(Board board) {
    this.board = board;
  }

  @Override
  public void setMove(int col) {
    move = col;
  }

  @Override
  public void play() {
    board.move(move);
  }
}