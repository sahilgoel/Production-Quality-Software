package edu.nyu.sg4187.connectfour.Model;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;
import edu.nyu.sg4187.connectfour.Model.Board;
import edu.nyu.sg4187.connectfour.Model.BoardBuilder;
import edu.nyu.sg4187.connectfour.Model.HumanPlayer;

public class HumanPlayerTest {
  private HumanPlayer humanPlayer;
  private Board board;

  /**
   * Setup
   * @throws Exception
   *           Nothing.
   */
  @Before
  public void setUp() throws Exception {
    board = new BoardBuilder().withCol(6).withRow(7).build();
    humanPlayer = new HumanPlayer(board);
  }

  /**
   * Test for setMove by human.
   */
  @Test
  public void setMoveTest()
      throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    humanPlayer.setMove(3);
    Field field = humanPlayer.getClass().getDeclaredField("move");
    field.setAccessible(true);
    int actual = (Integer) field.get(humanPlayer);
    field.setAccessible(false);
    int expected = 3;
    assertEquals(expected, actual);
  }

  /**
   * Test for simulate the human move.
   */
  @Test
  public void playTest() {
    humanPlayer.setMove(3);
    humanPlayer.play();
    int expected = 1;
    int actual = board.getBoardEntries()[6][3];
    assertEquals(expected, actual);
  }
}
