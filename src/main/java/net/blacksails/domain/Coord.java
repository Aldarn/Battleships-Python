package net.blacksails.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Pair representing a coordinate on the game board.
 */
public class Coord {
	public int x;
	public int y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isValid(GameBoard board) {
		if(x < 0 || y < 0 || x >= board.getSize() || y >= board.getSize()) {
			throw new RuntimeException("Coordinate '" + x + ", " + y + "'found outisde of board range (0, " + board.getSize() + ")");
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).
			append(x).
			append(y).
			toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Coord))
			return false;
		if (obj == this)
			return true;

		Coord rhs = (Coord) obj;
		return new EqualsBuilder().
			append(x, rhs.x).
			append(y, rhs.y).
			isEquals();		
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
