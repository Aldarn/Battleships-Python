package net.thoughtmachine.action;

import net.thoughtmachine.domain.Coord;
import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.domain.Ship;

public class MoveAction extends Action {
	@Override
	public Coord act(GameBoard board, Ship ship, Coord coord) {
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
}
