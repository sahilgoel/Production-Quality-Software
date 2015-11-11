package edu.nyu.sg4187.connectfour;

import edu.nyu.sg4187.connectfour.Controller.ConnectFourController;
import edu.nyu.sg4187.connectfour.View.ConnectFourUI;

public class GameMain {

  /**
   * Startup the Whole Game.
   * 
   * @param args
   */
  public static void main(String[] args) {
    ConnectFourController controller = new ConnectFourController();
    ConnectFourUI ui = new ConnectFourUI(controller);
    ui.gameReset();
    controller.setUiView(ui);
    controller.setBoard(ui.getBoard());
    controller.setPlayerOne(ui.getPlayer1());
    controller.setPlayerTwo(ui.getPlayer2());
    controller.switchToNextPlayer();
  }
}
