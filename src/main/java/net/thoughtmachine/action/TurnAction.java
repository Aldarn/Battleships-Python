package net.thoughtmachine.action;

import net.thoughtmachine.domain.Coord;
import net.thoughtmachine.domain.Direction;
import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.domain.Orientation;
import net.thoughtmachine.domain.Ship;

public class TurnAction extends Action {
	private Direction direction;
	
	public TurnAction(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public Coord act(GameBoard board, Ship ship, Coord coord) {
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
}
