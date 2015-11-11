package edu.nyu.sg4187.connectfour.Model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.nyu.sg4187.connectfour.Model.Board;
import edu.nyu.sg4187.connectfour.Model.ComputerPlayer;
import edu.nyu.sg4187.connectfour.Model.HumanPlayer;
import edu.nyu.sg4187.connectfour.Model.IPlayer;
import edu.nyu.sg4187.connectfour.Model.PlayerFactory;

public class PlayerFactoryTest {
  private IPlayer playerTest;

  /**
   * Test for creating a human player.
   */
  @Test
  public void factoryCreatingHumanPlayerTest() {
    playerTest = PlayerFactory.getPlayer("Human", null);
    assertTrue(playerTest instanceof HumanPlayer);
    assertFalse(playerTest instanceof ComputerPlayer);
  }

  /**
   * Test for creating a computer player.
   */
  @Test
  public void factoryCreatingComputerPlayerTest() {
    playerTest = PlayerFactory.getPlayer("Computer", Board.getInstance(3, 2));
    assertTrue(playerTest instanceof ComputerPlayer);
    assertFalse(playerTest instanceof HumanPlayer);
  }

  /**
   * Test for creating an arbitrary player, should return human player.
   */
  @Test
  public void factoryCreatingArbitraryPlayerTest() {
    playerTest = PlayerFactory.getPlayer("AnyString", null);
    assertTrue(playerTest instanceof HumanPlayer);
    assertFalse(playerTest instanceof ComputerPlayer);
  }
}
