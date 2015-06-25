package net.thoughtmachine.action;

import net.thoughtmachine.domain.Coord;
import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.domain.Ship;

public abstract class Action {
	public abstract Coord act(GameBoard board, Ship ship, Coord coord);
}
