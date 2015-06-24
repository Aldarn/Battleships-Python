package net.thoughtmachine.service;

import static org.junit.Assert.fail;

import org.junit.Test;

public class ClassicSimulationServiceTest {
	@Test
	public void testPlaySimulation() {
		fail();
	}
	
	public void testMoveOperationsApplyToBoard() {
		fail();
	}
	
	public void testTurnOperationsDontApplyToBoard() {
		fail();
	}
	
	public void testShootOperationsDontApplyToBoard() {
		fail();
	}
	
	/**
	 * Test that at the end of an operation a ship can share the location 
	 * of other ships providing all the other ships have been wrecked.
	 */
	public void testOperationEndShipCanShareLocationWithWrecks() {
		fail();
	}
	
	/**
	 * Test that at the end of an operation a ship cannot share the location 
	 * of other ships that have not been wrecked.
	 */
	public void testOperationEndNonWreckShipsSharingLocationThrowsException() {
		fail();
	}
}
