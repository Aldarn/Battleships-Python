package net.thoughtmachine.domain;

import java.util.ArrayList;
import java.util.List;

import net.thoughtmachine.action.Action;

public class GameModel extends ArrayList<List<Action>> {
	private static final long serialVersionUID = 42L;

	private GameBoard initialBoard;
	
	public GameModel(GameBoard initialBoard) {
		super();
		
		this.initialBoard = initialBoard;
	}
	
	public GameBoard getInitialBoard() {
		return initialBoard;
	}
}
