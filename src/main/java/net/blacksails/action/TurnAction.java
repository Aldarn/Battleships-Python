package net.blacksails.action;

import net.blacksails.domain.Coord;
import net.blacksails.domain.Direction;
import net.blacksails.domain.GameBoard;
import net.blacksails.domain.Orientation;
import net.blacksails.domain.Ship;

public class TurnAction extends Action {
	private Direction direction;
	
	public TurnAction(Direction direction) {
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	@Override
	public Coord act(GameBoard board, Ship ship, Coord coord) {
		validateShip(ship);
		
		// TODO: Fix this quick hack with a method on the Orientation enum that return the 
		// enum with number currentEnumNumber + 1%4 for right and currentEnumNumber + 3%4 for left
		switch(direction) {
			case LEFT:
				switch(ship.getOrientation()) {
					case NORTH:
						ship.setDirection(Orientation.WEST);
						break;
					case SOUTH:
						ship.setDirection(Orientation.EAST);
						break;
					case EAST:
						ship.setDirection(Orientation.NORTH);
						break;					
					case WEST:
						ship.setDirection(Orientation.SOUTH);
						break;
				}
				break;
			case RIGHT:
				switch(ship.getOrientation()) {
					case NORTH:
						ship.setDirection(Orientation.EAST);
						break;
					case SOUTH:
						ship.setDirection(Orientation.WEST);
						break;
					case EAST:
						ship.setDirection(Orientation.SOUTH);
						break;					
					case WEST:
						ship.setDirection(Orientation.NORTH);
						break;
				}
		}
		return coord;
	}
	
	/**
	 * Validates the ship.
	 * 
	 * TODO: This is common to Move and Turn actions - fix this
	 */
	private void validateShip(Ship ship) {
		if(ship == null) {
			throw new RuntimeException("Tried to turn non existent ship.");
		}
		
		if(ship.isSunk()) {
			throw new RuntimeException("Tried to turn sunken ship " + ship + ".");
		}
	}
}
