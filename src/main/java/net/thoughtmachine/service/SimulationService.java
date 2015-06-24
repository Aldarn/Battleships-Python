package net.thoughtmachine.service;

import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.domain.GameModel;

public interface SimulationService {
	GameBoard play(GameModel gameModel);
}
