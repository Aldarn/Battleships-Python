package net.thoughtmachine.domain;

public class Ship {
	private Coord coord;
	private Direction direction;
	private boolean isSunk = false;
	
	public Ship(Coord coord, Direction startDirection) {
		this.coord = coord;
		this.direction = startDirection;
	}
	
	public Coord getCoord() {
		return coord;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public boolean isSunk() {
		return isSunk;
	}
	
	public void setSunk(boolean isSunk) {
		this.isSunk = isSunk;
	}
}
