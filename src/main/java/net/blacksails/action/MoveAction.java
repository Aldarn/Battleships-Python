package net.blacksails.action;

import net.blacksails.domain.Coord;
import net.blacksails.domain.GameBoard;
import net.blacksails.domain.Ship;

public class MoveAction extends Action {
	@Override
	public Coord act(GameBoard board, Ship ship, Coord coord) {
		validateShip(ship);
		
		Coord newCoord = null;
		switch(ship.getOrientation()) {
			case NORTH:
				newCoord = new Coord(coord.x, coord.y + 1);	
				break;
			case SOUTH:
				newCoord = new Coord(coord.x, coord.y - 1);	
				break;
			case EAST:
				newCoord = new Coord(coord.x + 1, coord.y);	
				break;
			case WEST:
				newCoord = new Coord(coord.x - 1, coord.y);	
				break;
		}
		newCoord.isValid(board);
		return newCoord;
	}
	
	private void validateShip(Ship ship) {
		if(ship == null) {
			throw new RuntimeException("Tried to move non existent ship.");
		}
		
		if(ship.isSunk()) {
			throw new RuntimeException("Tried to move sunken ship " + ship + ".");
		}
	}
}
