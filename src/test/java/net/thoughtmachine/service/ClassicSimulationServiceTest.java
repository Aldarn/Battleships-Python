package net.thoughtmachine.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import net.thoughtmachine.action.MoveAction;
import net.thoughtmachine.action.ShootAction;
import net.thoughtmachine.action.TurnAction;
import net.thoughtmachine.domain.Coord;
import net.thoughtmachine.domain.Direction;
import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.domain.GameModel;
import net.thoughtmachine.domain.Operation;
import net.thoughtmachine.domain.Orientation;
import net.thoughtmachine.domain.Ship;

import org.junit.Before;
import org.junit.Test;

public class ClassicSimulationServiceTest {
	private GameBoard board;
	private Coord coord;
	private Ship ship;
	private Coord coordToSink;
	private Ship shipToSink;
	private Operation moveOperation;
	private Operation shootOperation;
	private GameModel model;
	private ClassicSimulationService simulationService;
	
	@Before
	public void setupModel() {
		// Setup the board
		board = new GameBoard(10);
		coord = new Coord(3, 5);
		ship = new Ship(coord, Orientation.EAST);
		List<Ship> shipList = new ArrayList<Ship>();
		shipList.add(ship);
		
		coordToSink = new Coord(9, 9);
		shipToSink = new Ship(coordToSink, Orientation.SOUTH);
		List<Ship> shipToSinkList = new ArrayList<Ship>();
		shipToSinkList.add(shipToSink);
		
		board.put(coord, shipList);
		board.put(coordToSink, shipToSinkList);
		
		// Create some operations
		moveOperation = new Operation(coord);
		moveOperation.add(new MoveAction());
		moveOperation.add(new TurnAction(Direction.LEFT));
		
		shootOperation = new Operation(coordToSink);
		shootOperation.add(new ShootAction());
		
		// Setup the model
		model = new GameModel(board);
		model.add(moveOperation);
		model.add(shootOperation);
		
		// Setup the service
		simulationService = new ClassicSimulationService();
	}
	
	@Test
	public void testPlaySimulation() {
		// -------------------------------------------------------
		GameBoard newBoard = simulationService.play(model);
		// -------------------------------------------------------
		assertEquals(newBoard.get(new Coord(4, 5)).get(0), ship);
		assertEquals(newBoard.get(coordToSink).get(0), shipToSink);
		assertTrue(newBoard.get(coordToSink).get(0).isSunk());
	}
	
	@Test
	public void testMoveOperationsApplied() {
		// -------------------------------------------------------
		GameBoard newBoard = simulationService.play(model);
		// -------------------------------------------------------
		Coord movedShipEndCoord = new Coord(4, 5);
		assertEquals(newBoard.get(coord).size(), 0);
		assertEquals(ship.getCoord(), movedShipEndCoord);
		assertEquals(newBoard.get(movedShipEndCoord).get(0), ship);
	}
	
	/**
	 * Test that at the end of an operation a ship can share the location 
	 * of other ships providing all the other ships have been wrecked.
	 */
	@Test
	public void testOperationEndShipCanShareLocationWithWrecks() {
		Coord movedShipEndCoord = new Coord(4, 5);
		Ship sunkShip = new Ship(movedShipEndCoord, Orientation.WEST);
		sunkShip.sink();
		List<Ship> sunkShipList = new ArrayList<Ship>();
		sunkShipList.add(sunkShip);
		board.put(movedShipEndCoord, sunkShipList);
		// -------------------------------------------------------
		GameBoard newBoard = simulationService.play(model);
		// -------------------------------------------------------
		assertEquals(newBoard.get(movedShipEndCoord).size(), 2);
	}
	
	/**
	 * Test that at the end of an operation a ship cannot share the location 
	 * of other ships that have not been wrecked.
	 */
	@Test(expected = RuntimeException.class)
	public void testOperationEndNonWreckShipsSharingLocationThrowsException() {
		Coord movedShipEndCoord = new Coord(4, 5);
		Ship newShip = new Ship(movedShipEndCoord, Orientation.WEST);
		List<Ship> newShipList = new ArrayList<Ship>();
		newShipList.add(newShip);
		board.put(movedShipEndCoord, newShipList);
		// -------------------------------------------------------
		simulationService.play(model);
		// -------------------------------------------------------
	}
}
