package net.thoughtmachine.action;

import net.thoughtmachine.domain.Coord;
import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.domain.Ship;

public class ShootAction extends Action {
	@Override
	public Coord act(GameBoard board, Ship ship, Coord coord) {
		if(ship != null) {
			if(ship.isSunk()) {
				throw new RuntimeException("Tried to sink already sunk ship" + ship + ".");
			} else {
				ship.sink();
			}
		}
		return coord;
	}
}
