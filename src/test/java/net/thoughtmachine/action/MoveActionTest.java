package net.thoughtmachine.action;

import static org.junit.Assert.assertEquals;
import net.thoughtmachine.domain.Coord;
import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.domain.Orientation;
import net.thoughtmachine.domain.Ship;

import org.junit.Test;

public class MoveActionTest {	
	@Test
	public void testMoveActionNorth() {
		Action action = new MoveAction();
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(5, 5);
		Ship ship = new Ship(coord, Orientation.NORTH);
		// -------------------------------------------------------
		Coord result = action.act(board, ship, coord);
		// -------------------------------------------------------
		assertEquals(result, new Coord(5, 6));
	}

	@Test
	public void testMoveActionSouth() {
		Action action = new MoveAction();
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(5, 5);
		Ship ship = new Ship(coord, Orientation.SOUTH);
		// -------------------------------------------------------
		Coord result = action.act(board, ship, coord);
		// -------------------------------------------------------
		assertEquals(result, new Coord(5, 4));
	}

	@Test
	public void testMoveActionEast() {
		Action action = new MoveAction();
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(5, 5);
		Ship ship = new Ship(coord, Orientation.EAST);
		// -------------------------------------------------------
		Coord result = action.act(board, ship, coord);
		// -------------------------------------------------------
		assertEquals(result, new Coord(6, 5));
	}
	
	@Test
	public void testMoveActionWest() {
		Action action = new MoveAction();
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(5, 5);
		Ship ship = new Ship(coord, Orientation.WEST);
		// -------------------------------------------------------
		Coord result = action.act(board, ship, coord);
		// -------------------------------------------------------
		assertEquals(result, new Coord(4, 5));
	}
	
	@Test(expected = RuntimeException.class)
	public void testMoveActionBeyondBoardDimensionsThrowsException() {
		Action action = new MoveAction();
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(0, 0);
		Ship ship = new Ship(coord, Orientation.SOUTH);
		// -------------------------------------------------------
		action.act(board, ship, coord);
		// -------------------------------------------------------
	}
	
	@Test(expected = RuntimeException.class)
	public void testMoveSunkenShipThrowsException() {
		Action action = new MoveAction();
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(0, 0);
		Ship ship = new Ship(coord, Orientation.SOUTH);
		ship.sink();
		// -------------------------------------------------------
		action.act(board, ship, coord);
		// -------------------------------------------------------
	}
	
	@Test(expected = RuntimeException.class)
	public void testMoveNoShipThrowsException() {
		Action action = new MoveAction();
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(0, 0);
		// -------------------------------------------------------
		action.act(board, null, coord);
		// -------------------------------------------------------
	}
}
