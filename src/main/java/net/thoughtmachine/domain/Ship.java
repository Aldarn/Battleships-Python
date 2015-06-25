package net.thoughtmachine.domain;

public class Ship {
	private Coord coord;
	private Orientation orientation;
	private boolean isSunk = false;
	
	public Ship(Coord coord, Orientation startOrientation) {
		this.coord = coord;
		this.orientation = startOrientation;
	}
	
	public Coord getCoord() {
		return coord;
	}
	
	public void setCoord(Coord coord) {
		this.coord = coord;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	public void setDirection(Orientation orientation) {
		this.orientation = orientation;
	}
	
	public boolean isSunk() {
		return isSunk;
	}
	
	public void sink() {
		this.isSunk = true;
	}
	
	@Override
	public String toString() {
		return "(" + coord.x + " " + coord.y + " " + orientation.getToken() + ")" + (isSunk ? " SUNK" : "");
	}
}
