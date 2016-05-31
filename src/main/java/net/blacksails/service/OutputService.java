package net.blacksails.service;

import java.io.IOException;

import net.blacksails.domain.GameBoard;
import net.blacksails.strategy.OutputStrategy;

public interface OutputService {
	void createOutput(OutputStrategy strategy, GameBoard board) throws IOException;
}
