package net.thoughtmachine.domain;

import java.util.HashMap;

public class GameBoard extends HashMap<Coord, Ship> {
	private static final long serialVersionUID = 42L;

	public GameBoard() {
		super();
	}
}
