package net.thoughtmachine.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BoardTest {
	@Test
	public void testGetNonSunk() {
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(1,1);
		Ship ship = new Ship(coord, Orientation.NORTH);
		Ship sunkShip = new Ship(coord, Orientation.SOUTH);
		sunkShip.sink();
		List<Ship> ships = new ArrayList<Ship>();
		ships.add(ship);
		ships.add(sunkShip);
		board.put(coord, ships);
		// -------------------------------------------------------
		Ship nonSunk = board.getNonSunk(coord);
		// -------------------------------------------------------
		assertNotNull(nonSunk);
		assertFalse(nonSunk.isSunk());
		assertEquals(nonSunk, ship);
	}
	
	@Test
	public void testGetNonSunkOnlySunk() {
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(1,1);
		Ship sunkShip = new Ship(coord, Orientation.SOUTH);
		sunkShip.sink();
		List<Ship> ships = new ArrayList<Ship>();
		ships.add(sunkShip);
		board.put(coord, ships);
		// -------------------------------------------------------
		Ship nonSunk = board.getNonSunk(coord);
		// -------------------------------------------------------
		assertNull(nonSunk);
	}
}
