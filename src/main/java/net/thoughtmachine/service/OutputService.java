package net.thoughtmachine.service;

import java.io.IOException;

import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.strategy.OutputStrategy;

public interface OutputService {
	void createOutput(OutputStrategy strategy, GameBoard board) throws IOException;
}
