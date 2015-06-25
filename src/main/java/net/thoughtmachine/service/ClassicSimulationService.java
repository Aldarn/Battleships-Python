package net.thoughtmachine.service;

import java.util.List;

import net.thoughtmachine.action.Action;
import net.thoughtmachine.domain.Coord;
import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.domain.GameModel;
import net.thoughtmachine.domain.Operation;
import net.thoughtmachine.domain.Ship;

public class ClassicSimulationService implements SimulationService {
	@Override
	public GameBoard play(GameModel gameModel) {
		GameBoard board = gameModel.getInitialBoard();
		
		// Run all operations
		for(Operation operation : gameModel) {
			Ship ship = board.getNonSunk(operation.getCoord());
			Coord coord = operation.getCoord();
			
			// Run each action in the operation, maintaining a pointer to the latest coord
			for(Action action : operation) {
				coord = action.act(board, ship, coord);
			}

			// Apply any new coords if relevant
			if(coord != operation.getCoord()) {
				// Check if there's a collision
				Ship otherNonSunkShip = board.getNonSunk(coord);
				if(otherNonSunkShip != null) {
					throw new RuntimeException("Ship " + ship + "ended on the same coordinate " + coord + 
						" as other ship " + otherNonSunkShip + "at the end of an operation.");
				}				
				
				// Update ship to the new coordinate
				ship.setCoord(coord);
				List<Ship> ships = board.get(coord);
				ships.add(ship);
				board.put(coord, ships);
				
				// Remove it from the old one
				board.get(operation.getCoord()).remove(ship);
			}
		}
		return board;
	}
}
