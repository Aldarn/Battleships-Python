package net.blacksails.action;

import net.blacksails.domain.Coord;
import net.blacksails.domain.GameBoard;
import net.blacksails.domain.Ship;

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
