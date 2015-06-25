package net.thoughtmachine.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
		ClassicInputParserService parserSpy = spy(parser);
		BufferedReader reader = mock(BufferedReader.class);
		InputStream inputStream = mock(InputStream.class);
		doReturn(reader).when(parserSpy).getReader(inputStream);
		when(reader.readLine()).thenReturn("10").thenReturn("(1, 2, H)").thenReturn(null);
		// -------------------------------------------------------
		parserSpy.parseInput(inputStream);
		// -------------------------------------------------------
	}
	
	@Test
	public void testShipCoordBeyondBoardSizeThrowsException() {
		fail();
	}
	
	@Test
	public void testParsesShootOperation() {
		fail();
	}
	
	@Test
	public void testParsesMoveOperation() {
		fail();
	}
	
	@Test
	public void testNoOperationContainsShootAndMove() {
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
