package net.blacksails.domain;

import java.util.ArrayList;

public class GameModel extends ArrayList<Operation> {
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
