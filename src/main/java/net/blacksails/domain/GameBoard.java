package net.blacksails.domain;

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
		List<Ship> shipsAtCoord = get(coord);
		if(shipsAtCoord != null) {
			for(Ship ship : shipsAtCoord) {
				if(!ship.isSunk()) {
					return ship;
				}
			}
		}
		return null;
	}
}
