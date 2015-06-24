package net.thoughtmachine.domain;

import java.util.HashMap;

public class GameBoard extends HashMap<Coord, Ship> {
	private static final long serialVersionUID = 42L;

	private int size;
	
	public GameBoard(int size) {
		super();
		
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
}
