package net.blacksails.strategy;

import java.util.List;

import net.blacksails.domain.GameBoard;
import net.blacksails.domain.Ship;

public class ClassicOutputStrategy implements OutputStrategy {
	@Override
	public String generateOutput(GameBoard board) {
		String output = "";
		for(List<Ship> ships : board.values()) {
			for(Ship ship : ships) {
				// TODO: Should use OS friendly carriage returns here
				output += ship + "\n";
			}
		}
		return output.substring(0, output.length() - 1);
	}
}
