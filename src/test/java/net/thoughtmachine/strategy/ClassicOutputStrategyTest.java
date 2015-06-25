package net.thoughtmachine.strategy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import net.thoughtmachine.domain.Coord;
import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.domain.Orientation;
import net.thoughtmachine.domain.Ship;

import org.junit.Before;
import org.junit.Test;

public class ClassicOutputStrategyTest {
	ClassicOutputStrategy outputStrategy;
	GameBoard board;
	Coord coord;
	Coord coord2;
	Ship ship;
	Ship ship2;
	
	@Before
	public void createObjects() {
		outputStrategy = new ClassicOutputStrategy();
		board = new GameBoard(10);
		coord = new Coord(3, 4);
		coord2 = new Coord(6, 2);
		ship = new Ship(coord, Orientation.WEST);
		ship2 = new Ship(coord2, Orientation.NORTH);
		List<Ship> shipList = new ArrayList<Ship>();
		shipList.add(ship);
		List<Ship> shipList2 = new ArrayList<Ship>();
		shipList2.add(ship2);
		board.put(coord, shipList);
		board.put(coord2, shipList2);
	}
	
	@Test
	public void testGetOutput() {
		// -------------------------------------------------------
		String output = outputStrategy.generateOutput(board);
		// -------------------------------------------------------
		assertEquals(output, "(3, 4, W)\n(6, 2, N)");
	}
	
	@Test
	public void testGetOutputIndicatesSunkShips() {
		ship2.sink();
		// -------------------------------------------------------
		String output = outputStrategy.generateOutput(board);
		// -------------------------------------------------------
		assertEquals(output, "(3, 4, W)\n(6, 2, N) SUNK");
	}
}
