package net.blacksails.strategy;

import net.blacksails.domain.GameBoard;

public interface OutputStrategy {
	String generateOutput(GameBoard board);
}
