package net.thoughtmachine.action;

import net.thoughtmachine.domain.Ship;

public abstract class Action {
	private Ship target;
	
	public Action(Ship target) {
		this.target = target;
	}
	
	public Ship getTarget() {
		return target;
	}
	
	abstract void act();
}
