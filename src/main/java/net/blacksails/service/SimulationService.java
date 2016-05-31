package net.blacksails.service;

import net.blacksails.domain.GameBoard;
import net.blacksails.domain.GameModel;

public interface SimulationService {
	GameBoard play(GameModel gameModel);
}
