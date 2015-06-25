package net.thoughtmachine.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import net.thoughtmachine.action.MoveAction;
import net.thoughtmachine.action.ShootAction;
import net.thoughtmachine.action.TurnAction;
import net.thoughtmachine.domain.Coord;
import net.thoughtmachine.domain.Direction;
import net.thoughtmachine.domain.GameModel;
import net.thoughtmachine.domain.Orientation;
import net.thoughtmachine.domain.Ship;

import org.junit.Test;

public class ClassicInputParserServiceTest {
	ClassicInputParserService parser = new ClassicInputParserService();
	
	@Test
	public void testParsesBoardSize() throws IOException {
		ClassicInputParserService parserSpy = spy(parser);
		BufferedReader reader = mock(BufferedReader.class);
		InputStream inputStream = mock(InputStream.class);
		doReturn(reader).when(parserSpy).getReader(inputStream);
		when(reader.readLine()).thenReturn("10").thenReturn(null);
		// -------------------------------------------------------
		GameModel model = parserSpy.parseInput(inputStream);
		// -------------------------------------------------------
		assertEquals(model.getInitialBoard().getSize(), 10);
	}
	
	@Test(expected = RuntimeException.class)
	public void testInvalidBoardSizeThrowsException() throws IOException {
		// TODO: This should be less brittle and test a more specific exception is thrown
		ClassicInputParserService parserSpy = spy(parser);
		BufferedReader reader = mock(BufferedReader.class);
		InputStream inputStream = mock(InputStream.class);
		doReturn(reader).when(parserSpy).getReader(inputStream);
		when(reader.readLine()).thenReturn("-5").thenReturn(null);
		// -------------------------------------------------------
		parserSpy.parseInput(inputStream);
		// -------------------------------------------------------
	}
	
	@Test
	public void testParsesShips() throws IOException {
		ClassicInputParserService parserSpy = spy(parser);
		BufferedReader reader = mock(BufferedReader.class);
		InputStream inputStream = mock(InputStream.class);
		doReturn(reader).when(parserSpy).getReader(inputStream);
		when(reader.readLine()).thenReturn("10").thenReturn("(1, 2, N) (9, 4, E)").thenReturn(null);
		// -------------------------------------------------------
		GameModel model = parserSpy.parseInput(inputStream);
		// -------------------------------------------------------
		assertEquals(model.getInitialBoard().size(), 2);
		
		int i = 0;
		for(List<Ship> ships : model.getInitialBoard().values()) {
			if(i == 0) {
				assertEquals(ships.get(0).getCoord().x, 1);
				assertEquals(ships.get(0).getCoord().y, 2);
				assertEquals(ships.get(0).getOrientation(), Orientation.NORTH);
			} else {
				assertEquals(ships.get(0).getCoord().x, 9);
				assertEquals(ships.get(0).getCoord().y, 4);
				assertEquals(ships.get(0).getOrientation(), Orientation.EAST);
			}
			i++;
		}
	}
	
	@Test(expected = RuntimeException.class)
	public void testShipUnknownOrientationThrowsException() throws IOException {
		// TODO: This should be less brittle and test a more specific exception is thrown
		ClassicInputParserService parserSpy = spy(parser);
		BufferedReader reader = mock(BufferedReader.class);
		InputStream inputStream = mock(InputStream.class);
		doReturn(reader).when(parserSpy).getReader(inputStream);
		when(reader.readLine()).thenReturn("10").thenReturn("(1, 2, H)").thenReturn(null);
		// -------------------------------------------------------
		parserSpy.parseInput(inputStream);
		// -------------------------------------------------------
	}
	
	@Test(expected = RuntimeException.class)
	public void testShipCoordBeyondBoardSizeThrowsException() throws IOException {
		// TODO: This should be less brittle and test a more specific exception is thrown
		ClassicInputParserService parserSpy = spy(parser);
		BufferedReader reader = mock(BufferedReader.class);
		InputStream inputStream = mock(InputStream.class);
		doReturn(reader).when(parserSpy).getReader(inputStream);
		when(reader.readLine()).thenReturn("5").thenReturn("(6, 5, E)").thenReturn(null);
		// -------------------------------------------------------
		parserSpy.parseInput(inputStream);
		// -------------------------------------------------------
	}
	
	@Test
	public void testParsesShootOperation() throws IOException {
		ClassicInputParserService parserSpy = spy(parser);
		BufferedReader reader = mock(BufferedReader.class);
		InputStream inputStream = mock(InputStream.class);
		doReturn(reader).when(parserSpy).getReader(inputStream);
		when(reader.readLine()).thenReturn("10").thenReturn("(1, 2, N)").thenReturn("(1, 2)").thenReturn(null);
		// -------------------------------------------------------
		GameModel model = parserSpy.parseInput(inputStream);
		// -------------------------------------------------------
		assertEquals(model.size(), 1);
		assertEquals(model.get(0).getCoord(), new Coord(1, 2));
		assertEquals(model.get(0).size(), 1);
		assertTrue(model.get(0).get(0) instanceof ShootAction);
	}
	
	@Test
	public void testParsesMoveOperation() throws IOException {
		ClassicInputParserService parserSpy = spy(parser);
		BufferedReader reader = mock(BufferedReader.class);
		InputStream inputStream = mock(InputStream.class);
		doReturn(reader).when(parserSpy).getReader(inputStream);
		when(reader.readLine()).thenReturn("10").thenReturn("(3, 4, S)").thenReturn("(3, 4) MRL").thenReturn(null);
		// -------------------------------------------------------
		GameModel model = parserSpy.parseInput(inputStream);
		// -------------------------------------------------------
		assertEquals(model.size(), 1);
		assertEquals(model.get(0).getCoord(), new Coord(3, 4));
		assertEquals(model.get(0).size(), 3);
		assertTrue(model.get(0).get(0) instanceof MoveAction);
		assertTrue(model.get(0).get(1) instanceof TurnAction);
		assertEquals(((TurnAction)model.get(0).get(1)).getDirection(), Direction.RIGHT);
		assertTrue(model.get(0).get(2) instanceof TurnAction);
		assertEquals(((TurnAction)model.get(0).get(2)).getDirection(), Direction.LEFT);
	}
	
	@Test
	public void testNoOperationContainsShootAndMove() throws IOException {
		ClassicInputParserService parserSpy = spy(parser);
		BufferedReader reader = mock(BufferedReader.class);
		InputStream inputStream = mock(InputStream.class);
		doReturn(reader).when(parserSpy).getReader(inputStream);
		when(reader.readLine()).thenReturn("10").thenReturn("(3, 4, S)").thenReturn("(3, 4) MRL")
			.thenReturn("(");
		// -------------------------------------------------------
		GameModel model = parserSpy.parseInput(inputStream);
		// -------------------------------------------------------
		fail();
	}
	
	@Test
	public void testInvalidOperationCoordinatesThrowsException() {
		fail();
	}
	
	@Test
	public void testMalformedOperationThrowsException() {
		fail();
	}
}
