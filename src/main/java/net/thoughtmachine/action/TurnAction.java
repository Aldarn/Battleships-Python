package net.thoughtmachine.action;

import net.thoughtmachine.domain.Direction;
import net.thoughtmachine.domain.Ship;

public class TurnAction extends Action {
	private Direction direction;
	
	public TurnAction(Ship target, Direction direction) {
		super(target);
		
		this.direction = direction;
	}
	
	@Override
	public void act() {
		// TODO
	}
}
