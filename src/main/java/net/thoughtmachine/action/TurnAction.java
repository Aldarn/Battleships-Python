package net.thoughtmachine.action;

import net.thoughtmachine.domain.Coord;
import net.thoughtmachine.domain.Direction;

public class TurnAction extends Action {
	private Direction direction;
	
	public TurnAction(Coord target, Direction direction) {
		super(target);
		
		this.direction = direction;
	}
	
	@Override
	public void act() {
		// TODO
	}
}
