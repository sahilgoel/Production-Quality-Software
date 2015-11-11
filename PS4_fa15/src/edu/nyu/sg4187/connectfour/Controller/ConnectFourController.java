package edu.nyu.sg4187.connectfour.Controller;

import edu.nyu.sg4187.connectfour.Model.Board;
import edu.nyu.sg4187.connectfour.Model.ComputerPlayer;
import edu.nyu.sg4187.connectfour.Model.IPlayer;
import edu.nyu.sg4187.connectfour.View.*;

public class ConnectFourController {

  private ConnectFourUI uiView;
  private IPlayer playerOne;
  private IPlayer playerTwo;
  private IPlayer currentPlayer;
  private Board board;

  public void setCurrentPlayer(IPlayer player) {
    this.currentPlayer = player;
  }

  public void setUiView(ConnectFourUI uiView) {
    this.uiView = uiView;
  }

  public void setPlayerOne(IPlayer playerOne) {
    this.playerOne = playerOne;
  }

  public void setPlayerTwo(IPlayer playerTwo) {
    this.playerTwo = playerTwo;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  /**
   * Constructor. Set currentPlayer.
   */
  public ConnectFourController() {
    currentPlayer = playerTwo;
  }

  /**
   * Execute one move by human.
   * 
   * @param col
   *          The column get from ActionListener
   */
  public void humanPlay(int col) {
    currentPlayer.setMove(col);
    currentPlayer.play();
    uiView.boardRefresh();
    switchToNextPlayer();
  }

  /**
   * Always switch to next Player.
   */
  public void switchToNextPlayer() {
    if (board.isOver()) {
      uiView.gameOver();
    } else {
      currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
      if (currentPlayer instanceof ComputerPlayer) {
        computerPlay();
      }
    }
  }

  /**
   * Execute one step moving by computer.
   */
  private void computerPlay() {
    currentPlayer.play();
    uiView.boardRefresh();
    switchToNextPlayer();
  }

}