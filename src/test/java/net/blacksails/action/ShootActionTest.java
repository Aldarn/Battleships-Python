package net.blacksails.action;

import static org.junit.Assert.assertEquals;
import net.blacksails.domain.Coord;
import net.blacksails.domain.GameBoard;
import net.blacksails.domain.Orientation;
import net.blacksails.domain.Ship;

import org.junit.Test;

public class ShootActionTest {
	@Test
	public void testShootActionMiss() {
		Action action = new ShootAction();
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(1, 1);
		// -------------------------------------------------------
		action.act(board, null, coord);
		// -------------------------------------------------------
	}
	
	@Test
	public void testShootActionHit() {
		Action action = new ShootAction();
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(1, 1);
		Ship ship = new Ship(coord, Orientation.SOUTH);
		// -------------------------------------------------------
		action.act(board, ship, coord);
		// -------------------------------------------------------
		assertEquals(ship.isSunk(), true);
	}
	
	@Test(expected = RuntimeException.class)
	public void testShootActionSunkShipThrowsException() {
		Action action = new ShootAction();
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(1, 1);
		Ship ship = new Ship(coord, Orientation.SOUTH);
		ship.sink();
		// -------------------------------------------------------
		action.act(board, ship, coord);
		// -------------------------------------------------------
	}
}
