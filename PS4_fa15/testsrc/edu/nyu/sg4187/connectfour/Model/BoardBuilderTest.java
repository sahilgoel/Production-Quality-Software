package edu.nyu.sg4187.connectfour.Model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.nyu.sg4187.connectfour.Model.Board;
import edu.nyu.sg4187.connectfour.Model.BoardBuilder;

public class BoardBuilderTest {

  /**
   * Test the board builder to verify whether can generate a new board.
   */
  @Test
  public void boardBuilderTest() {
    Board board = new BoardBuilder().withCol(7).withRow(6).build();
    assertTrue(board instanceof Board);
  }
}
