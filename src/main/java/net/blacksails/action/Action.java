package net.blacksails.action;

import net.blacksails.domain.Coord;
import net.blacksails.domain.GameBoard;
import net.blacksails.domain.Ship;

public abstract class Action {
	public abstract Coord act(GameBoard board, Ship ship, Coord coord);
}
