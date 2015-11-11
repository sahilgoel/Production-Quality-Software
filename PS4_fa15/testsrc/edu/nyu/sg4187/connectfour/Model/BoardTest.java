package edu.nyu.sg4187.connectfour.Model;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import edu.nyu.sg4187.connectfour.Model.Board;
import edu.nyu.sg4187.connectfour.Model.BoardBuilder;
import edu.nyu.sg4187.connectfour.Model.Board.Coordinate;

public class BoardTest {
  private Board board;

  /**
   * Setup for the board.
   * @throws Exception (Ignored)
   */
  @Before
  public void setUp() throws Exception {
    board = new BoardBuilder().withCol(7).withRow(6).build();
  }

  /**
   * Test whether the two boards are same or not.
   */
  @Test
  public void getInstanceTest() {
    Board boardTest = Board.getInstance(6, 7);
    assertEquals(board, boardTest);
  }

  /**
   * Test the getColumnState method(To check if number of dots in
   * specified column are returned correctly)
   */
  @Test
  public void getColumnStateTest() {
    board.move(0);
    int actual = board.getColumnState(0);
    int expected = 1;
    assertEquals(expected, actual);
  }

  /**
   * Test for a invaild move.
   */
  @Test(expected = RuntimeException.class)
  public void moveInvalidTest() {
    board.move(-1);
    board.move(100);
  }

  /**
   * Test for a valid undo move.
   */
  @Test
  public void undoMoveSuccessTest() {
    for (int i = 0; i < 6; i++) {
      board.move(0);
    }

    int actual = board.getBoardEntries()[0][0];
    int expected = 2;
    assertEquals(expected, actual);

    board.undoMove();
    actual = board.getBoardEntries()[0][0];
    expected = 0;
    assertEquals(expected, actual);
  }

  /**
   * Test for a invalid undo move.
   */
  @Test
  public void undoMoveFailTest() {
    board.move(0);
    board.undoMove();
    assertFalse(board.undoMove());
  }

  /**
   * Test once column is full, exception can be thrown.
   */
  @Test(expected = RuntimeException.class)
  public void moveColumnFullTest() {
    for (int i = 0; i < 6; i++) {
      board.move(0);
    }
    board.move(0);
  }

  /**
   * Test the getter for the moveX.
   */
  @Test
  public void getMoveX() {
    board.move(3);
    board.move(3);
    int expected = 4;
    int actual = board.getMoveX();
    assertEquals(expected, actual);
  }

  /**
   * Test the getter for the moveY.
   */
  @Test
  public void getMoveY() {
    board.move(4);
    int expected = 4;
    int actual = board.getMoveY();
    assertEquals(expected, actual);
  }

  /**
   * Test the getter for the board Array.
   */
  @Test
  public void getBoardDataArrayTest() {
    board.move(2);
    board.move(2);
    int expected = 2;
    int actual = board.getBoardEntries()[4][2];
    assertEquals(expected, actual);
  }

  /**
   * Test the method for nextPlayer.
   */
  @Test
  public void nextPlayerTest() {
    board.move(4);
    board.move(4);
    int expected = 1;
    int actual = board.nextPlayer();
    assertEquals(expected, actual);
  }

  /**
   * Test the method for getWinnerNumber.
   */
  @Test
  public void getWinnerNumberTest() {
    board.move(0);
    board.move(1);
    board.move(0);
    board.move(1);
    board.move(0);
    board.move(1);
    board.move(0);
    board.isOver();
    int expected = 1;
    int actual = board.getWinnerNumber();
    assertEquals(expected, actual);

  }

  /**
   * Test for the tie result.
   */
  @Test
  public void isOverFullTest() {
    for (int j = 0; j < 3; j++) {
      for (int i = 0; i < 6; i++) {
        board.move(j);
      }
    }

    for (int j = 5; j < 7; j++) {
      for (int i = 0; i < 6; i++) {
        board.move(j);
      }
    }

    board.move(4);

    for (int i = 0; i < 6; i++) {
      board.move(3);
    }

    for (int i = 0; i < 5; i++) {
      board.move(4);
    }

    int expectedWiner = -1;
    int actualWiner = board.getWinnerNumber();
    assertTrue(board.isOver());
    assertEquals(expectedWiner, actualWiner);
  }

  /**
   * Test for not Over.
   */
  @Test
  public void isOverNotOverTest() {
    for (int j = 0; j < 3; j++) {
      for (int i = 0; i < 6; i++) {
        board.move(j);
      }
    }

    for (int j = 5; j < 7; j++) {
      for (int i = 0; i < 6; i++) {
        board.move(j);
      }
    }

    board.move(4);

    for (int i = 0; i < 6; i++) {
      board.move(3);
    }

    assertFalse(board.isOver());
  }

  /**
   * Test for the over situation with 4 vertical dots.
   */
  @Test
  public void isOverBottomTest() {
    for (int i = 0; i < 3; i++) {
      board.move(0);
      board.move(1);
    }
    board.move(0);
    assertTrue(board.isOver());

    int expectedWiner = 1;
    int actualWiner = board.getWinnerNumber();
    assertEquals(expectedWiner, actualWiner);
  }

  /**
   * Test for the over situation with 4 horizontal dots.
   */
  @Test
  public void isOverHorizonTest() {
    for (int i = 0; i < 3; i++) {
      board.move(i);
      board.move(i);
    }
    board.move(3);
    assertTrue(board.isOver());
    int expectedWiner = 1;
    int actualWiner = board.getWinnerNumber();
    assertEquals(expectedWiner, actualWiner);
  }

  /**
   * Test for the over situation with 4 dots in diagonal.
   */
  @Test
  public void isOverDiagonalTest() {
    for (int i = 0; i < 3; i = i + 2) {
      for (int j = 0; j < 4; j++) {
        board.move(i);
      }
    }
    board.move(4);
    board.move(3);
    board.move(4);
    for (int i = 0; i < 3; i++) {
      board.move(1);
    }
    assertTrue(board.isOver());
    int expectedWiner = 2;
    int actualWiner = board.getWinnerNumber();
    assertEquals(expectedWiner, actualWiner);
  }

  /**
   * Test for the over situation with 4 dots in anti-diagonal.
   */
  @Test
  public void isOverAntiDiagonalTest() {
    for (int i = 0; i < 3; i = i + 2) {
      board.move(i);
      board.move(i);
    }
    board.move(2);
    for (int i = 1; i < 3; i = i + 2) {
      board.move(i);
      board.move(i);
    }
    for (int i = 0; i < 4; i++) {
      board.move(3);
    }
    assertTrue(board.isOver());
    int expectedWiner = 1;
    int actualWiner = board.getWinnerNumber();
    assertEquals(expectedWiner, actualWiner);
  }

  /**
   * Test for the getter for the winStep list.
   */
  @Test
  public void getWinStepTest() {
    for (int i = 0; i < 3; i++) {
      board.move(0);
      board.move(1);
    }
    board.move(0);
    board.isOver();
    List<Coordinate> winStep = board.getWinningSteps();
    int actual = winStep.size();
    int expected = 4;
    assertEquals(expected, actual);
  }

}
