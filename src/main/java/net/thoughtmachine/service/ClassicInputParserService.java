package net.thoughtmachine.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.thoughtmachine.domain.Coord;
import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.domain.GameModel;
import net.thoughtmachine.domain.Orientation;
import net.thoughtmachine.domain.Ship;

public class ClassicInputParserService implements InputParserService {
	private static final int BOARD_SIZE_LINE = 0;
	private static final int SHIPS_LINE = 1;
	
	private static final Pattern SHIP_PATTERN = Pattern.compile("(\\(\\d+, \\d+, \\w\\))\\s*");
	private static final Pattern SHIP_VALUES_PATTERN = Pattern.compile("\\((\\d+), (\\d+), (\\w)\\)");
	
	private static final String ORIENTATION_NORTH = "N";
	private static final String ORIENTATION_SOUTH = "S";
	private static final String ORIENTATION_EAST = "E";
	private static final String ORIENTATION_WEST = "W";
	
	@Override
	public GameModel parseInput(InputStream inputStream) {
	    BufferedReader reader = getReader(inputStream);
    	String line;
    	int lineNumber = 0;
    	GameBoard initialBoard = null;
    	GameModel gameModel = null;
	    
	    try {
	    	while ((line = reader.readLine()) != null) {
	    		switch(lineNumber) {
	    			case BOARD_SIZE_LINE:
	    				initialBoard = createGameBoard(line);
	    				gameModel = new GameModel(initialBoard);
	    				break;
	    			case SHIPS_LINE:
	    				loadShips(initialBoard, line);
	    				break;
	    			default:
	    				loadOperation(line);
	    		}
	    		
	    		lineNumber++;
	    	}
	    } catch (IOException e) {
	    	throw new RuntimeException(e);
	    }
	    
	    return gameModel;
	}
	
	public BufferedReader getReader(InputStream inputStream) {
		return new BufferedReader(new InputStreamReader(inputStream));
	}
	
	private GameBoard createGameBoard(String sizeLine) {
		int boardSize = Integer.parseInt(sizeLine);
		
		if(boardSize < 1) {
			throw new RuntimeException("Board size '" + boardSize + "' is invalid, must be > 0.");
		}
		return new GameBoard(boardSize);
	}
	
	private void loadShips(GameBoard board, String ships) {
		Matcher shipMatcher = SHIP_PATTERN.matcher(ships);
		if(shipMatcher.matches()) {
			for(int i = 1; i < shipMatcher.groupCount(); i++) {
				Matcher shipValuesMatcher = SHIP_VALUES_PATTERN.matcher(shipMatcher.group(i));
				if(shipValuesMatcher.matches()) {
					Coord coord = new Coord(Integer.valueOf(shipValuesMatcher.group(1)), 
						Integer.valueOf(shipValuesMatcher.group(1)));
					if(board.get(coord) != null) {
						throw new RuntimeException("Two ships found starting in the same location (" + coord + ").");
					} else {
						List<Ship> shipSet = new ArrayList<Ship>();
						shipSet.add(new Ship(coord, getOrientationFromString(ships)));
						board.put(coord, shipSet);
					}
				}
			}
		}
	}
	
	private Orientation getOrientationFromString(String orientation) {
		switch(orientation) {
			case ORIENTATION_NORTH:
				return Orientation.NORTH;
			case ORIENTATION_SOUTH:
				return Orientation.SOUTH;
			case ORIENTATION_EAST:
				return Orientation.EAST;
			case ORIENTATION_WEST:
				return Orientation.WEST;
		}
		
		throw new RuntimeException("Could not determine orientation from string '" + orientation + "'.");
	}
	
	private void loadOperation(String operation) {
		
	}
}
