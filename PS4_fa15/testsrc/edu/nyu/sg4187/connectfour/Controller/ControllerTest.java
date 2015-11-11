package edu.nyu.sg4187.connectfour.Controller;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Test;
import edu.nyu.sg4187.connectfour.Controller.ConnectFourController;
import edu.nyu.sg4187.connectfour.Model.Board;
import edu.nyu.sg4187.connectfour.Model.IPlayer;
import edu.nyu.sg4187.connectfour.Model.PlayerFactory;
import edu.nyu.sg4187.connectfour.View.ConnectFourUI;

public class ControllerTest {

  private Board board;
  private IPlayer player1;
  private IPlayer player2;
  private ConnectFourUI uiShell;
  private ConnectFourController controller;

  /**
   * Setup for the test.
   * 
   * @throws Exception
   *           Nothing.
   */
  @Before
  public void setUp() throws Exception {
    controller = new ConnectFourController();
    uiShell = new ConnectFourUI(controller);
    uiShell.gameReset();
    board = uiShell.getBoard();
    player1 = uiShell.getPlayer1();
    player2 = uiShell.getPlayer2();
    controller.setUiView(uiShell);
    controller.setBoard(uiShell.getBoard());
    controller.setPlayerOne(uiShell.getPlayer1());
    controller.setPlayerTwo(uiShell.getPlayer2());

  }

  /**
   * Test the human player's move.
   */
  @Test
  public void humanPlayTest() {
    controller.setCurrentPlayer(player1);
    controller.humanPlay(0);
    int actual = board.getBoardEntries()[5][0];
    int expected = 1;
    assertEquals(expected, actual);
  }

  /**
   * Test computer player's move.
   * 
   * @throws SecurityException
   *           Should not be thrown.
   * @throws NoSuchMethodException
   *           Should not be thrown.
   * @throws IllegalArgumentException
   *           Should not be thrown.
   * @throws IllegalAccessException
   *           Should not be thrown.
   * @throws InvocationTargetException
   *           Should not be thrown.
   */
  @Test
  public void computerPlayTest() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
      IllegalAccessException, InvocationTargetException {
    player2 = PlayerFactory.getPlayer("Computer", board);
    controller.setPlayerOne(player2);
    controller.setPlayerTwo(player1);
    controller.setCurrentPlayer(player2);

    Method method = controller.getClass().getDeclaredMethod("computerPlay", new Class[] {});
    method.setAccessible(true);
    method.invoke(controller, new Object[] {});
    boolean isNotNull = false;
    for (int i = 0; i < 6; i++) {
      if (board.getBoardEntries()[5][i] != 0) {
        isNotNull = true;
        break;
      }
    }
    assertTrue(isNotNull);
  }

  /**
   * Test the switchToNextPlayer method whether it can converge to the end.
   */
  @Test
  public void switchToNextPlayerTest() {
    player1 = PlayerFactory.getPlayer("Computer", board);
    player2 = PlayerFactory.getPlayer("Computer", board);
    controller.setPlayerOne(player1);
    controller.setPlayerTwo(player2);
    controller.setCurrentPlayer(player2);
    controller.switchToNextPlayer();
    assertTrue(board.isOver());
  }

}
