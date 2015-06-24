package net.thoughtmachine.service;

import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.strategy.OutputStrategy;

public interface OutputService {
	void createOutput(OutputStrategy strategy, GameBoard board);
}
