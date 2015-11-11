package edu.nyu.sg4187.connectfour.Model;

public interface IPlayer {
  /**
   * setMove method created to distinguish human from computer.
   * 
   * @param col
   */
  public void setMove(int col);

  /**
   * Use the implemented strategy to move a step in the board.
   */
  public void play();
}