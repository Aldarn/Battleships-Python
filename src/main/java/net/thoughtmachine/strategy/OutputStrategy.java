package net.thoughtmachine.strategy;

import net.thoughtmachine.domain.GameBoard;

public interface OutputStrategy {
	String generateOutput(GameBoard board);
}
