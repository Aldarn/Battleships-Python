package net.blacksails.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CoordTest {
	@Test
	public void testIsValid() {
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(2, 2);
		// -------------------------------------------------------
		boolean isValid = coord.isValid(board);
		// -------------------------------------------------------
		assertTrue(isValid);
	}
	
	@Test(expected = RuntimeException.class)
	public void testBelowBoardThrowsException() {
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(-1, 2);
		// -------------------------------------------------------
		boolean isValid = coord.isValid(board);
		// -------------------------------------------------------
	}
	
	@Test(expected = RuntimeException.class)
	public void testAboveBoardThrowsException() {
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(9, 10);
		// -------------------------------------------------------
		boolean isValid = coord.isValid(board);
		// -------------------------------------------------------
	}
}
