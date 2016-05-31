package net.blacksails.domain;

import java.util.ArrayList;

import net.blacksails.action.Action;

public class Operation extends ArrayList<Action> {
	private static final long serialVersionUID = 42;
	
	private Coord coord;
	
	public Operation(Coord coord) {
		this.coord = coord;
	}
	
	public Coord getCoord() {
		return coord;
	}
}
