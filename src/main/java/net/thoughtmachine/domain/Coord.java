package net.thoughtmachine.domain;

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
