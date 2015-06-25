package net.thoughtmachine.action;

import net.thoughtmachine.domain.Coord;

public abstract class Action {
	private Coord target;
	
	public Action(Coord target) {
		this.target = target;
	}
	
	public Coord getTarget() {
		return target;
	}
	
	abstract void act();
}
