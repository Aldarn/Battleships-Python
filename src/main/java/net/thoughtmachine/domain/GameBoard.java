package net.thoughtmachine.domain;

import java.util.HashMap;
import java.util.List;

public class GameBoard extends HashMap<Coord, List<Ship>> {
	private static final long serialVersionUID = 42L;

	private int size;
	
	public GameBoard(int size) {
		super();
		
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	public Ship getNonSunk(Coord coord) {
		for(Ship ship : get(coord)) {
			if(ship.isSunk()) {
				return ship;
			}
		}
		return null;
	}
}
