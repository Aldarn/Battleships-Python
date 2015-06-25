package net.thoughtmachine.action;

import static org.junit.Assert.assertEquals;
import net.thoughtmachine.domain.Coord;
import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.domain.Orientation;
import net.thoughtmachine.domain.Ship;

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
